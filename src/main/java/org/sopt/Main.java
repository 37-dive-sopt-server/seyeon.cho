package org.sopt;

import org.sopt.controller.MemberController;
import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.repository.MemoryMemberRepository;
import org.sopt.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private static final MemberServiceImpl memberServiceImpl = new MemberServiceImpl();
    private static final MemberController memberController = new MemberController();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerMember();
                    break;
                case "2":
                    findMemberById();
                    break;
                case "3":
                    findAllMembers();
                    break;
                case "4":
                    deleteMember();
                    break;
                case "5":
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
                    scanner.close();
                    return;
                default:
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
            }
        }
    }


    private static void printMenu() {
        System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
        System.out.println("---------------------------------");
        System.out.println("1️⃣. 회원 등록 ➕");
        System.out.println("2️⃣. ID로 회원 조회 🔍");
        System.out.println("3️⃣. 전체 회원 조회 📋");
        System.out.println("4️⃣. 회원 삭제 🗑️");
        System.out.println("5️⃣. 종료 🚪");
        System.out.println("---------------------------------");
        System.out.print("메뉴를 선택하세요: ");
    }

    private static void registerMember() {
        try {
            System.out.print("등록할 회원 이름을 입력하세요: ");
            String name = scanner.nextLine();
            System.out.print("등록할 회원 이메일을 입력하세요: ");
            String email = scanner.nextLine();
            System.out.print("등록할 회원 생년월일(YYYY-MM-DD)을 입력하세요: ");
            String birthdate = scanner.nextLine();
            System.out.print("등록할 회원 성별(MALE / FEMALE)을 입력하세요: ");
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

            if (name.trim().isEmpty() || email.trim().isEmpty() || birthdate.trim().isEmpty()) {
                System.out.println("⚠️ 모든 정보를 입력해주세요.");
                return;
            }

            Long createdId = memberController.createMember(name, email, birthdate, gender);
            System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ 성별을 MALE 또는 FEMALE로 정확히 입력해주세요.");
        } catch (IllegalStateException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void findMemberById() {
        try {
            System.out.print("조회할 회원 ID를 입력하세요: ");
            Long id = Long.parseLong(scanner.nextLine());
            Optional<Member> foundMemberOpt = memberController.findMemberById(id);

            if (foundMemberOpt.isPresent()) {
                Member foundMember = foundMemberOpt.get();
                System.out.println("--- ✅ 조회된 회원 정보 ---");
                System.out.println("ID: " + foundMember.getId());
                System.out.println("이름: " + foundMember.getName());
                System.out.println("이메일: " + foundMember.getEmail());
                System.out.println("생년월일: " + foundMember.getBirthdate());
                System.out.println("성별: " + foundMember.getGender());
                System.out.println("--------------------------");
            } else {
                System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
        }
    }

    private static void findAllMembers() {
        List<Member> allMembers = memberController.getAllMembers();
        if (allMembers.isEmpty()) {
            System.out.println("ℹ️ 등록된 회원이 없습니다.");
        } else {
            System.out.println("--- 📋 전체 회원 목록 📋 ---");
            for (Member member : allMembers) {
                System.out.println("👤 ID=" + member.getId() + ", 이름=" + member.getName() + ", 이메일=" + member.getEmail() + ", 생년월일=" + member.getBirthdate() + ", 성별=" + member.getGender());
            }
            System.out.println("--------------------------");
        }
    }

    private static void deleteMember() {
        try {
            System.out.print("삭제할 회원 ID를 입력하세요: ");
            Long id = Long.parseLong(scanner.nextLine());
            if (memberController.findMemberById(id).isPresent()) {
                memberController.deleteMemberById(id);
                System.out.println("✅ ID " + id + "번 회원이 삭제되었습니다.");
            } else {
                System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
        }
    }
}