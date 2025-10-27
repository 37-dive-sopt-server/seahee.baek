package org.sopt.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.sopt.domain.Member;
import org.sopt.domain.enums.GENDER;
import org.sopt.exception.MyException;
import org.sopt.exception.code.FileErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("fileRepo")
public class FileMemberRepository implements MemberRepository {

	private final Path dataFile;
	private final AtomicLong nextId = new AtomicLong(1);

	@Autowired
	public FileMemberRepository(Path dataFile) {
		this.dataFile = dataFile;
		initStorage();
		this.nextId.set(getFinalId() + 1);
	}

	@Override
	public synchronized Member saveMember(String name, LocalDate birthday, String email, GENDER gender) {
		Member member = new Member(nextId.getAndIncrement(), name, birthday, email, gender);
		try (BufferedWriter writer = Files.newBufferedWriter(dataFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			writer.write(String.join(",",
				String.valueOf(member.getId()),
				member.getName(),
				member.getBirthday().toString(),
				member.getEmail(),
				member.getGender().toString()
			));
			writer.newLine();
		} catch (Exception e) {
			throw new MyException(FileErrorCode.FILE_WRITE_FAILED);
		}
		return member;
	}


	@Override
	public Optional<Member> findById(Long id) {
		return readAll().stream()
			.filter(m -> m.getId().equals(id))
			.findFirst();
	}

	@Override
	public List<Member> findAll() {
		return readAll();
	}

	@Override
	public Optional<Member> findByEmail(String email) {
		return readAll().stream()
			.filter(m -> m.getEmail().equals(email))
			.findFirst();
	}

	@Override
	public synchronized void deleteById(Long memberId) {
		List<Member> members = readAll();
		members.removeIf(m -> m.getId().equals(memberId));
		saveAll(members);
	}

	private Long getFinalId() {
		return readAll().stream()
			.mapToLong(Member::getId)
			.max()
			.orElse(0L);
	}

	private List<Member> readAll() {
		List<Member> members = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(dataFile, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] t = line.split(",", -1);
				Long id = Long.parseLong(t[0]);
				String name = t[1];
				LocalDate birthday = LocalDate.parse(t[2]);
				String email = t[3];
				GENDER gender = GENDER.fromString(t[4]);
				members.add(new Member(id, name, birthday, email, gender));
			}
		} catch (Exception e) {
			throw new MyException(FileErrorCode.FILE_READ_FAILED);
		}
		return members;
	}

	private void saveAll(List<Member> members) {
		try (BufferedWriter writer = Files.newBufferedWriter(
			dataFile, StandardCharsets.UTF_8,
			StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

			for (Member m : members) {
				writer.write(String.join(",",
					String.valueOf(m.getId()),
					m.getName(),
					m.getBirthday().toString(),
					m.getEmail(),
					m.getGender().toString()
				));
				writer.newLine();
			}
		} catch (Exception e) {
			throw new MyException(FileErrorCode.FILE_SAVE_FAILED);
		}
	}

	private void initStorage() {
		try {
			Files.createDirectories(dataFile.getParent());
			if (Files.notExists(dataFile)) {
				Files.createFile(dataFile);
			}
		} catch (Exception e) {
			throw new MyException(FileErrorCode.FILE_CREATE_FAILED);
		}
	}
}
