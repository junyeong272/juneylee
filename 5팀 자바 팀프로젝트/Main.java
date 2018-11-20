
import java.io.IOException;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		while(true){
		Member member = new Member();
		int a=-1,d=-1;
		System.out.println("----------------------------------------");
		System.out.println("경북대학교 전자공학부 자바 팀프로젝트 '5팀'\n담당교수:소현주 교수님");
		System.out.println("\n\t[CMD로 즐기는 편의점]\n");
		System.out.println("\t\t\t2014104272 이준영\n\t\t\t2014104101 김창현\n\t\t\t2014104140 박재현");
		System.out.println("----------------------------------------");
		System.out.println("0.프로그램 종료\n1.소비자 모드\n2.관리자모드");
		System.out.print("모드를 선택하세요: ");
		int mode1 = input.nextInt();
		
		switch(mode1){
		case 0: //프로그램 종료
			System.out.println("\nCMD로 즐기는 편의점을 이용해주셔서 감사합니다.\n-프로그램을 종료합니다.-");
			System.exit(0);
			break;
			
		case 1: //1.소비자 모드
			
			//소비자 확인을 위한 아이디,비밀번호 입력 및 판단 과정 (로그인 기능) 추가하기
			boolean tf = true;
			while(tf){
				System.out.println("-------------------------------------");
				System.out.println("[소비자 모드]");
				System.out.println("\n0.이전 화면\n1.로그인\n2.회원가입");
				System.out.print("메뉴를 선택하세요:");
				int select = input.nextInt();
				if(select == 0){ //1-0.이전 화면
					System.out.println("\n->이전 화면으로 돌아갑니다.");
					System.out.println("-------------------------------------");
					break;
				}else if(select==1){//1-1.로그인
					member.printMemberLogin();
					String consumer = member.name.get(member.getIndex()); //consumer에 로그인한 소비자의 이름 저장
					a=-1;
					//소비자 모드 실행
					while(a!=0){
						System.out.println("-------------------------------------");
						System.out.println("[소비자 모드]");
						System.out.println("\n0.이전 화면\n1.상품 선택\n2.상품 검색\n3.장바구니 및 구매\n4.구매내역\n5.회원정보 확인");
						System.out.print("실행하려는 목록을 선택하세요: ");
						int mode2 = input.nextInt();
						int b=-1;
						switch(mode2){
						case 0: //1-1-0.이전 화면으로
							System.out.println("\n->이전 화면으로 돌아갑니다.");								
							a=0;
							break;
						case 1: //1-1-1.상품 선택
							while(b!=0){
								System.out.println("-------------------------------------");
								System.out.println("[상품선택 카테고리]");
								System.out.println("\n0.이전 화면으로\n1.담배 코너\n2.과자 코너\n3.음료수 코너\n4.라면 코너\n5.냉동식품 코너\n6.아이스크림 코너\n7.생필품 코너");
								System.out.print("원하시는 카테고리를 선택하세요: ");
								int choice = input.nextInt();
								switch(choice){
								case 0: //1-1-1-0.이전 화면으로
									System.out.println("\n->이전 화면으로 돌아갑니다.");
									System.out.println("-------------------------------------");
									b=0;
									break;
								case 1: //1-1-1-1.담배 코너
									Category.printCategory("담배",consumer);
									break;
								case 2: //1-1-1-2.과자 코너
									Category.printCategory("과자",consumer);
									break;
								case 3: //1-1-1-3.음료수 코너
									Category.printCategory("음료수",consumer);
									break;
								case 4: //1-1-1-4.라면 코너
									Category.printCategory("라면",consumer);
									break;
								case 5: //1-1-1-5.냉동식품 코너
									Category.printCategory("냉동식품",consumer);
									break;
								case 6: //1-1-1-6.아이스크림 코너
									Category.printCategory("아이스크림",consumer);
									break;
								case 7: //1-1-1-7.생필품 코너
									Category.printCategory("생필품",consumer);
									break;
								}
							}
							break;	
						case 2: //1-1-2.상품 검색
							Category.printSearchGoods();
							break;
						case 3: //1-1-3.장바구니 및 구매
							Cart.printCart(consumer);
							break;	
						case 4: //1-1-4.구매내역
							Cart.printPurchaseList(consumer);
							break;
						case 5: //1-1-5.회원정보 확인(추가 부분)
							member.printMemberInformation(); //회원정보 출력
							
							System.out.println("\n0.이전 화면\n1.회원정보 수정\n2.회원 탈퇴");
							System.out.print("선택하세요: ");
							int choice = input.nextInt();
							switch(choice){
								case 0: // 1-1-5-0.이전 화면
									System.out.println("\n->이전 화면으로 돌아갑니다.");
									System.out.println("-------------------------------------");
									break;
								case 1: // 1-1-5-1.회원정보 수정
									member.modifyMeberInformation();
									break;
								case 2: // 1-1-5-2.회원 탈퇴
									member.printDeleteMember();
									if(member.getIndex()==-1){
										a=0;
										tf=false;
									}else
										break;
							}	
							break;
						}
					}
					
				}else if(select ==2){//1-2.회원가입
					member.printMembership();
					tf=false;
				}
			}
			break;
		case 2: //2.관리자 모드
			
			//관리자 확인을 위한 비밀번호 입력 및 판단 과정
			AdminPassword psw = new AdminPassword();
			psw.printCheckAdmin();
			
			//관리자 모드 실행
			while(d!=0){
				int e=-1;
				System.out.println("[관리자 모드]");
				System.out.println("\n0.이전 화면으로\n1.상품 관리\n2.구매내역 및 매출 확인\n3.물품 관리내역 확인\n4.관리자 암호 변경\n5.소비자회원 관리");
				System.out.print("실행하려는 목록을 선택하세요: ");
				int mode3 = input.nextInt();
				switch(mode3){
				case 0: //2-0.이전 화면으로
					System.out.println("\n->이전 화면으로 돌아갑니다.");
					d=0;
					break;
				case 1: //2-1.상품 관리
					while(e!=0){
						System.out.println("-------------------------------------");
						System.out.println("[상품 관리]");
						System.out.println("\n0.이전 화면으로\n1.상품 품목 확인\n2.상품 추가\n3.상품 제거\n4.상품 수량 추가\n5.상품 가격 변경");
						System.out.print("실행하려는 목록을 선택하세요: ");
						int mode4 = input.nextInt();
						switch(mode4){
						case 0: //2-1-0.이전 화면으로
							System.out.println("\n->이전 화면으로 돌아갑니다.");
							System.out.println("-------------------------------------");
							e=0;
							break;
						case 1: //2-1-1.상품 품목 확인
							Management.printGoodsCheck();
							break;
						case 2: //2-1-2.상품 추가
							Management.printGoodsAdd();
							break;
						case 3: //2-1-3.상품 제거
							Management.printDeleteGoods();
							break;
						case 4: //2-1-4.상품 수량 추가
							Management.printCountAdd();
							break;
						case 5: //2-1-5.상품 가격 변경
							Management.printuPriceChange();
							break;
						}
					}
					break;				
				case 2: //2-2.구매내역 및 매출 확인
					Management.printConfirmSales();
					break;
				case 3: //2-3.물품 관리내역 확인
					Management.printGoodsManagementList();
					break;
				case 4: //2-4.관리자 암호 변경
					psw.printChangePsw();
					break;
				case 5://2-5.소비자회원 관리
					member.printManegeMember();
					break;
				}
			}
			
		}
		}
	}
	
}
