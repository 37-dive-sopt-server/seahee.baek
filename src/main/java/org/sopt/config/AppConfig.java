package org.sopt.config;

import java.nio.file.Files;
import java.nio.file.Path;

import org.sopt.controller.MemberController;
import org.sopt.exception.FileIOException;
import org.sopt.exception.util.ErrorMessage;
import org.sopt.repository.FileMemberRepository;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.MemoryMemberRepository;
import org.sopt.service.MemberService;
import org.sopt.service.MemberServiceImpl;

public class AppConfig {

	private static final Path DATA_DIR_PATH = Path.of("data/member");
	private static final Path DATA_FILE_PATH = DATA_DIR_PATH.resolve("members.csv");

	public MemberRepository memberRepository() {
		if (dirExistsChecker()) {
			fileExistsChecker();
			System.out.println("파일 저장 디렉토리가 존재하므로 파일 저장소를 사용합니다.");
			return new FileMemberRepository(DATA_FILE_PATH);
		}
		else {
			System.out.println("파일 저장 디렉토리가 존재하지 않으므로 메모리 저장소를 사용합니다.");
			return new MemoryMemberRepository(); }
	}

	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	public MemberController memberController() {
		return new MemberController(memberService());
	}

	private boolean dirExistsChecker() {
		return Files.isDirectory(DATA_DIR_PATH);
	}

	private void fileExistsChecker() {
		if (!Files.exists(DATA_FILE_PATH)) {
			try {
				Files.createFile(DATA_FILE_PATH);
			} catch (Exception e) {
				throw new FileIOException(ErrorMessage.FILE_CREATE_FAILED.getMessage());
			}
		}
	}

}
