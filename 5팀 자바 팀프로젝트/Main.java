
import java.io.IOException;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		while(true){
		Member member = new Member();
		int a=-1,d=-1;
		System.out.println("----------------------------------------");
		System.out.println("��ϴ��б� ���ڰ��к� �ڹ� ��������Ʈ '5��'\n��米��:������ ������");
		System.out.println("\n\t[CMD�� ���� ������]\n");
		System.out.println("\t\t\t2014104272 ���ؿ�\n\t\t\t2014104101 ��â��\n\t\t\t2014104140 ������");
		System.out.println("----------------------------------------");
		System.out.println("0.���α׷� ����\n1.�Һ��� ���\n2.�����ڸ��");
		System.out.print("��带 �����ϼ���: ");
		int mode1 = input.nextInt();
		
		switch(mode1){
		case 0: //���α׷� ����
			System.out.println("\nCMD�� ���� �������� �̿����ּż� �����մϴ�.\n-���α׷��� �����մϴ�.-");
			System.exit(0);
			break;
			
		case 1: //1.�Һ��� ���
			
			//�Һ��� Ȯ���� ���� ���̵�,��й�ȣ �Է� �� �Ǵ� ���� (�α��� ���) �߰��ϱ�
			boolean tf = true;
			while(tf){
				System.out.println("-------------------------------------");
				System.out.println("[�Һ��� ���]");
				System.out.println("\n0.���� ȭ��\n1.�α���\n2.ȸ������");
				System.out.print("�޴��� �����ϼ���:");
				int select = input.nextInt();
				if(select == 0){ //1-0.���� ȭ��
					System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
					System.out.println("-------------------------------------");
					break;
				}else if(select==1){//1-1.�α���
					member.printMemberLogin();
					String consumer = member.name.get(member.getIndex()); //consumer�� �α����� �Һ����� �̸� ����
					a=-1;
					//�Һ��� ��� ����
					while(a!=0){
						System.out.println("-------------------------------------");
						System.out.println("[�Һ��� ���]");
						System.out.println("\n0.���� ȭ��\n1.��ǰ ����\n2.��ǰ �˻�\n3.��ٱ��� �� ����\n4.���ų���\n5.ȸ������ Ȯ��");
						System.out.print("�����Ϸ��� ����� �����ϼ���: ");
						int mode2 = input.nextInt();
						int b=-1;
						switch(mode2){
						case 0: //1-1-0.���� ȭ������
							System.out.println("\n->���� ȭ������ ���ư��ϴ�.");								
							a=0;
							break;
						case 1: //1-1-1.��ǰ ����
							while(b!=0){
								System.out.println("-------------------------------------");
								System.out.println("[��ǰ���� ī�װ�]");
								System.out.println("\n0.���� ȭ������\n1.��� �ڳ�\n2.���� �ڳ�\n3.����� �ڳ�\n4.��� �ڳ�\n5.�õ���ǰ �ڳ�\n6.���̽�ũ�� �ڳ�\n7.����ǰ �ڳ�");
								System.out.print("���Ͻô� ī�װ��� �����ϼ���: ");
								int choice = input.nextInt();
								switch(choice){
								case 0: //1-1-1-0.���� ȭ������
									System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
									System.out.println("-------------------------------------");
									b=0;
									break;
								case 1: //1-1-1-1.��� �ڳ�
									Category.printCategory("���",consumer);
									break;
								case 2: //1-1-1-2.���� �ڳ�
									Category.printCategory("����",consumer);
									break;
								case 3: //1-1-1-3.����� �ڳ�
									Category.printCategory("�����",consumer);
									break;
								case 4: //1-1-1-4.��� �ڳ�
									Category.printCategory("���",consumer);
									break;
								case 5: //1-1-1-5.�õ���ǰ �ڳ�
									Category.printCategory("�õ���ǰ",consumer);
									break;
								case 6: //1-1-1-6.���̽�ũ�� �ڳ�
									Category.printCategory("���̽�ũ��",consumer);
									break;
								case 7: //1-1-1-7.����ǰ �ڳ�
									Category.printCategory("����ǰ",consumer);
									break;
								}
							}
							break;	
						case 2: //1-1-2.��ǰ �˻�
							Category.printSearchGoods();
							break;
						case 3: //1-1-3.��ٱ��� �� ����
							Cart.printCart(consumer);
							break;	
						case 4: //1-1-4.���ų���
							Cart.printPurchaseList(consumer);
							break;
						case 5: //1-1-5.ȸ������ Ȯ��(�߰� �κ�)
							member.printMemberInformation(); //ȸ������ ���
							
							System.out.println("\n0.���� ȭ��\n1.ȸ������ ����\n2.ȸ�� Ż��");
							System.out.print("�����ϼ���: ");
							int choice = input.nextInt();
							switch(choice){
								case 0: // 1-1-5-0.���� ȭ��
									System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
									System.out.println("-------------------------------------");
									break;
								case 1: // 1-1-5-1.ȸ������ ����
									member.modifyMeberInformation();
									break;
								case 2: // 1-1-5-2.ȸ�� Ż��
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
					
				}else if(select ==2){//1-2.ȸ������
					member.printMembership();
					tf=false;
				}
			}
			break;
		case 2: //2.������ ���
			
			//������ Ȯ���� ���� ��й�ȣ �Է� �� �Ǵ� ����
			AdminPassword psw = new AdminPassword();
			psw.printCheckAdmin();
			
			//������ ��� ����
			while(d!=0){
				int e=-1;
				System.out.println("[������ ���]");
				System.out.println("\n0.���� ȭ������\n1.��ǰ ����\n2.���ų��� �� ���� Ȯ��\n3.��ǰ �������� Ȯ��\n4.������ ��ȣ ����\n5.�Һ���ȸ�� ����");
				System.out.print("�����Ϸ��� ����� �����ϼ���: ");
				int mode3 = input.nextInt();
				switch(mode3){
				case 0: //2-0.���� ȭ������
					System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
					d=0;
					break;
				case 1: //2-1.��ǰ ����
					while(e!=0){
						System.out.println("-------------------------------------");
						System.out.println("[��ǰ ����]");
						System.out.println("\n0.���� ȭ������\n1.��ǰ ǰ�� Ȯ��\n2.��ǰ �߰�\n3.��ǰ ����\n4.��ǰ ���� �߰�\n5.��ǰ ���� ����");
						System.out.print("�����Ϸ��� ����� �����ϼ���: ");
						int mode4 = input.nextInt();
						switch(mode4){
						case 0: //2-1-0.���� ȭ������
							System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
							System.out.println("-------------------------------------");
							e=0;
							break;
						case 1: //2-1-1.��ǰ ǰ�� Ȯ��
							Management.printGoodsCheck();
							break;
						case 2: //2-1-2.��ǰ �߰�
							Management.printGoodsAdd();
							break;
						case 3: //2-1-3.��ǰ ����
							Management.printDeleteGoods();
							break;
						case 4: //2-1-4.��ǰ ���� �߰�
							Management.printCountAdd();
							break;
						case 5: //2-1-5.��ǰ ���� ����
							Management.printuPriceChange();
							break;
						}
					}
					break;				
				case 2: //2-2.���ų��� �� ���� Ȯ��
					Management.printConfirmSales();
					break;
				case 3: //2-3.��ǰ �������� Ȯ��
					Management.printGoodsManagementList();
					break;
				case 4: //2-4.������ ��ȣ ����
					psw.printChangePsw();
					break;
				case 5://2-5.�Һ���ȸ�� ����
					member.printManegeMember();
					break;
				}
			}
			
		}
		}
	}
	
}
