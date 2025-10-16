package org.sopt.util.view;

import java.util.Scanner;

public class MemberView {

	private final Scanner scanner = new Scanner(System.in);

	public void printAllMenuPrompt() {
		System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
		System.out.println("---------------------------------");
		System.out.println("1️⃣. 회원 등록 ➕");
		System.out.println("2️⃣. ID로 회원 조회 🔍");
		System.out.println("3️⃣. 전체 회원 조회 📋");
		System.out.println("4️⃣. ID로 회원 삭제 ❌");
		System.out.println("5️⃣. 종료 🚪");
		System.out.println("---------------------------------");
	}

	public String printMenuSelectPrompt() {
		System.out.print("메뉴를 선택하세요: ");
		return(scanner.nextLine().trim());
	}

	public String printNamePrompt() {
		System.out.print("등록할 회원 이름을 입력하세요(e.g. 백세희): ");
		return(scanner.nextLine().trim());
	}

	public String printBirthdayPrompt() {
		System.out.print("생일을 입력해주세요(e.g. 2003-06-17): ");
		return(scanner.nextLine().trim());
	}

	public String printEmailPrompt() {
		System.out.print("이메일을 입력해주세요(e.g. sae2@gmail.com): ");
		return(scanner.nextLine().trim());
	}

	public String printGenderPrompt() {
		System.out.print("성별을 입력해주세요(e.g. 남자 or 여자): ");
		return(scanner.nextLine().trim());
	}

	public String printDeleteMemberPrompt() {
		System.out.print("삭제하고 싶은 회원의 ID를 입력하세요: ");
		return(scanner.nextLine().trim());
	}

}