
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class Management{
	private static String codeNo; //��ǰ �ڵ��ȣ
	private static String gName; //��ǰ��
	private static int uPrice; //����
	private static int count; //����
	private static int total_sales; //�� �����
	private static String time; //�ð�
	private static String consumer; //�Һ��� �̸�
	private static String[] txtName={"���","����","�����","���","�õ���ǰ","���̽�ũ��","����ǰ","��ǰ ǰ��"}; //ī�װ� �ؽ�Ʈ���� �̸�
	public static Scanner input = new Scanner(System.in);
	
	public Management(){
	}
	
	//���� ����� �޼ҵ�(����� ������ txt���Ͽ� ���) 
	public static void overWrite(String txtN,Category o) throws IOException{
		FileWriter fw = new FileWriter(txtN+".txt");
		for(int i=0;i<o.array.size();i++)
			fw.write(o.array.get(i)+"\n"); //�ؽ�Ʈ ���Ͽ� �߰��� ��ǰ���� ������ �ֽ�ȭ
		fw.flush();
		fw.close();
	}
	
	//���ι��� "2-1-1.��ǰ ǰ�� Ȯ��" �ڳ� ���� �޼ҵ�
	public static void printGoodsCheck() throws IOException{	
		//"��ǰ ǰ��" �ؽ�Ʈ ���Ͽ� ��� ī�װ� ��ǰ�� �����Ű��
		FileWriter fw = new FileWriter("��ǰ ǰ��.txt");
		for(int i=0;i<txtName.length-1;i++){
			Category goods = new Category(txtName[i]);
			for(int j=0;j<goods.codeNo.size();j++)
				fw.write(goods.toString(j)+"\n");
			
		}
		fw.flush();
		fw.close();	
		
		//��ǰ ǰ�� Ȯ�� �ڳ� ����
		System.out.println("-------------------------------------");
		System.out.println("[��ǰ ǰ�� Ȯ��]");
		System.out.println("\n0.���� ȭ������\n1.���  2.����  3.�����  4.��� 5.�õ���ǰ\n6.���̽�ũ��  7.����ǰ  8.��ü����");
		System.out.print("���ϴ� ��ȣ�� �����ϼ��� : ");
		int n = input.nextInt();
		if(n!=0){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" �ڳ� ��Ȳ]");
			System.out.format("%-7s%-10s%-7s%-7s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)");
			for(int i=0;i<goods.codeNo.size();i++)
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));			
			while(true){
				System.out.println("\n0.���� ȭ������");
				System.out.print("���� : ");
				int select = input.nextInt();
				if(select==0){
					System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
					break;
				}
			}
		}
	}
	
	//���ι��� "2-1-2.��ǰ �߰�" �ڳ� ���� �޼ҵ�
	public static void printGoodsAdd() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[��ǰ �߰�]");
		System.out.println("\n0.���� ȭ������\n1.���  2.����  3.�����  4.���\n5.�õ���ǰ  6.���̽�ũ��  7.����ǰ");
		System.out.print("�߰��� ��ǰ�� ī�װ��� �����ϼ���:");
		int n = input.nextInt();
		if(n!=0&&n!=8){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" �ڳ� ��Ȳ]");
			System.out.format("%-7s%-10s%-7s%-7s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)");
			for(int i=0;i<goods.codeNo.size();i++)
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));	
			System.out.print("\n�߰��� ��ǰ �ڵ��ȣ:");
			codeNo = input.next();
			if(goods.codeNo.contains(codeNo)){ //��ǰ�� �߰��ϱ� ���� �Է��� �ڵ��ȣ�� �̹� �����ϸ� �ߺ����� �ʵ��� ����
				System.out.println("\n�̹� �����ϴ� �ڵ��ȣ �Դϴ�.");
				System.out.println("->���� ȭ������ ���ư��ϴ�.");
			}else{
				System.out.print("�߰��� ��ǰ ��ǰ��: ");
				gName = input.next();
				if(goods.gName.contains(gName)){ //�߰��Ϸ��� ��ǰ�� �̹� �����ϴ� ��ǰ�̸� �ߺ����� �ʵ��� ����
					System.out.println("\n�̹� �����ϴ� ��ǰ�� �Դϴ�.");
					System.out.println("->���� ȭ������ ���ư��ϴ�.");
				}else{
					System.out.print("�߰��� ��ǰ ����: ");
					uPrice = input.nextInt();
					System.out.print("�߰��� ��ǰ ����: ");
					count = input.nextInt();
				
					//��ǰ �������� txt���Ͽ� ������ ����
					FileWriter fw = new FileWriter("��ǰ ��������.txt",true);
					fw.write(Cart.getRunTime()+" ��ǰ �߰� => ��ǰ����: "+codeNo.toUpperCase()+" "+gName+" "+uPrice+"�� "+count+"��\n");
					fw.flush();
					fw.close();
			
					//�Է¹��� ������ �̿��Ͽ� �ش� �ؽ�Ʈ ���Ͽ� ��ǰ�� �߰���
					goods.array.add(codeNo.toUpperCase()+" "+gName+" "+uPrice+" "+count); //���ο� ��ǰ �߰�
					Collections.sort(goods.array); //�ڵ��ȣ ����
		
					overWrite(txtName[n-1],goods);//����� ������ txt���Ͽ� ���	
			
					System.out.println("\n��ǰ�� �߰� �Ǿ����ϴ�.");
					System.out.println("->���� ȭ������ ���ư��ϴ�.");
				}
			}
		}
			
	}

	//���ι��� "2-1-3.��ǰ ����" �ڳ� ���� �޼ҵ�
	public static void printDeleteGoods() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[��ǰ ����]");
		System.out.println("\n0.���� ȭ������\n1.���  2.����  3.�����  4.���\n5.�õ���ǰ  6.���̽�ũ��  7.����ǰ");
		System.out.print("������ ��ǰ�� ī�װ��� �����ϼ���:");
		int n = input.nextInt();
		if(n!=0&&n!=8){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" �ڳ� ��Ȳ]");
			System.out.format("%-7s%-10s%-7s%-7s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)");
			for(int i=0;i<goods.codeNo.size();i++){
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));
			}
			System.out.print("\n������ ��ǰ �ڵ��ȣ:");
			codeNo = input.next();
			
			if(goods.codeNo.contains(codeNo)){
				//��ǰ �������� txt���Ͽ� ������ ����
				FileWriter fw = new FileWriter("��ǰ ��������.txt",true);
				fw.write(Cart.getRunTime()+" ��ǰ ���� => ������ ��ǰ�� �ڵ��ȣ: "+codeNo.toUpperCase()+"\n");
				fw.flush();
				fw.close();
			
				//�Է¹��� �ڵ��ȣ�� �̿��Ͽ� �ش� ��ǰ�� ������
				for(int i=0;i<goods.codeNo.size();i++){
					if(goods.codeNo.get(i).equalsIgnoreCase(codeNo)){ //�ڵ��ȣ ��ҹ��� �������� ��
						goods.array.remove(i);
						goods.codeNo.remove(i);
						break;
					}			
				}
				overWrite(txtName[n-1],goods);//����� ������ txt���Ͽ� ���
				System.out.println("\n��ǰ�� ���� �Ǿ����ϴ�.");
				System.out.println("->���� ȭ������ ���ư��ϴ�.");
			}else
				System.out.println("�ش��ϴ� �ڵ��ȣ�� �����ϴ�.\n->���� ȭ������ ���ư��ϴ�.");
				
		}
	}
	
	//���ι��� "2-1-4.��ǰ ���� �߰�" �ڳ� ���� �޼ҵ�
	public static void printCountAdd() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[��ǰ ���� �߰�]");
		System.out.println("\n0.���� ȭ������\n1.���  2.����  3.�����  4.���\n5.�õ���ǰ  6.���̽�ũ��  7.����ǰ");
		System.out.print("������ �߰��� ��ǰ�� ī�װ��� �����ϼ���:");
		int n = input.nextInt();
		if(n!=0){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" �ڳ� ��Ȳ]");
			System.out.format("%-7s%-10s%-7s%-7s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)");
			for(int i=0;i<goods.codeNo.size();i++){
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));
			}
			System.out.print("\n������ �߰��� ��ǰ �ڵ��ȣ:");
			codeNo = input.next();
			if(goods.codeNo.contains(codeNo)){
				System.out.print("�߰��� ���� ����(������� "+goods.count.get(goods.codeNo.indexOf(codeNo))+"):");
				count = input.nextInt();
		
				//��ǰ �������� txt���Ͽ� ������ ����
				FileWriter fw = new FileWriter("��ǰ ��������.txt",true);
				fw.write(Cart.getRunTime()+" ��ǰ ���� �߰� => �ڵ��ȣ: "+codeNo.toUpperCase()+", �߰��� ����: "+count+"\n");
				fw.flush();
				fw.close();
			
				//�Է¹��� �ڵ��ȣ�� �̿��Ͽ� �ش� �ؽ�Ʈ ���Ͽ� ������ �߰���
				for(int i=0;i<goods.codeNo.size();i++){
					if(goods.codeNo.get(i).equalsIgnoreCase(codeNo)){//�ڵ��ȣ ��ҹ��� �������� ��
						int newCount = goods.count.get(i)+count;
						if(newCount<0) //���� ����� ������ 0���� �۾����� ������ 0���� ���� (������ 0���� ���� �� ���� ����)
							newCount=0;
						goods.count.set(i,newCount); //�Է¹��� �ڵ��ȣ�� �ش��ϴ� ��ǰ�� ������ �Է¹��� �߰������� ������
						goods.array.set(i,goods.codeNo.get(i)+" "+goods.gName.get(i)+" "+goods.uPrice.get(i)+" "+goods.count.get(i)); //array �ֽ�ȭ
					}
				}
				overWrite(txtName[n-1],goods);//����� ������ txt���Ͽ� ���
		
				System.out.println("\n������ ���� �Ǿ����ϴ�.");
				System.out.println("->���� ȭ������ ���ư��ϴ�.");
			}else
				System.out.println("�ش��ϴ� �ڵ��ȣ�� �����ϴ�.\n->���� ȭ������ ���ư��ϴ�.");
			
		}
	}
	
	//���ι��� "2-1-5.��ǰ ���� ����" �ڳ� ���� �޼ҵ�
	public static void printuPriceChange() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[��ǰ ���� ����]");
		System.out.println("\n0.���� ȭ������\n1.���  2.����  3.�����  4.���\n5.�õ���ǰ  6.���̽�ũ��  7.����ǰ");
		System.out.print("������ ������ ��ǰ�� ī�װ��� �����ϼ���:");
		int n = input.nextInt();
		if(n!=0){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" �ڳ� ��Ȳ]");
			System.out.format("%-7s%-10s%-7s%-7s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)");
			for(int i=0;i<goods.codeNo.size();i++){
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));
			}
			System.out.print("\n������ ������ ��ǰ �ڵ��ȣ:");
			codeNo = input.next();
			if(goods.codeNo.contains(codeNo)){
				System.out.print("������ ���� ����:");
				uPrice = input.nextInt();
		
				//�Է¹��� �ڵ��ȣ�� �̿��Ͽ� �ش� �ؽ�Ʈ ���Ͽ� ������ ������
				for(int i=0;i<goods.codeNo.size();i++){
					if(goods.codeNo.get(i).equalsIgnoreCase(codeNo)){//�ڵ��ȣ ��ҹ��� �������� ��
					
						//��ǰ �������� txt���Ͽ� ������ ����
						FileWriter fw = new FileWriter("��ǰ ��������.txt",true);
						fw.write(Cart.getRunTime()+" ��ǰ ���� ���� => �ڵ��ȣ: "+goods.codeNo.get(i)+", ���� ����: "+uPrice+"\n");
						fw.flush();
						fw.close();
					
						goods.uPrice.set(i,uPrice); //�Է¹��� �ڵ��ȣ�� �ش��ϴ� ��ǰ�� ������ ����ڿ��� �Է¹��� �������� ����
						goods.array.set(i,goods.codeNo.get(i)+" "+goods.gName.get(i)+" "+goods.uPrice.get(i)+" "+goods.count.get(i)); //array �ֽ�ȭ
					}
				}
				overWrite(txtName[n-1],goods);//����� ������ txt���Ͽ� ���
				System.out.println("\n������ ���� �Ǿ����ϴ�.");
				System.out.println("->���� ȭ������ ���ư��ϴ�.");
			}else
				System.out.println("�ش��ϴ� �ڵ��ȣ�� �����ϴ�.\n->���� ȭ������ ���ư��ϴ�.");
		}
	}
	
	//���ι��� "2-2.���ų��� �� ���� Ȯ��" �ڳ� ���� �޼ҵ�
	public static void printConfirmSales() throws Exception{
		System.out.println("-------------------------------------");
		System.out.println("[���ų��� �� ���� Ȯ��]");
		Cart salesList = new Cart("���ų���");
		total_sales=0;
		System.out.format("%-5s%-7s%-10s%-7s%-7s%-10s%s\n","������","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)","�հ�(��)","���� �ð�");
		for(int i=0;i<salesList.codeNo.size();i++){
			System.out.format("%-5s%-10s%-6s\t%7d%10d%10d%20s\n",salesList.consumer.get(i),salesList.codeNo.get(i),salesList.gName.get(i),salesList.uPrice.get(i),salesList.count.get(i),salesList.total.get(i),salesList.time.get(i));
			total_sales += salesList.total.get(i);
		}
		System.out.println("�� �����: "+total_sales+"��");
		while(true){
			System.out.println("\n0.���� ȭ������\n1.���ų��� �ʱ�ȭ");
			System.out.print("���� : ");
			int select = input.nextInt();
			if(select==0){
				System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
				System.out.println("-------------------------------------");
				break;
			}else if(select==1){
				Cart.resetTxt("���ų���");
				System.out.println("���ų����� �ʱ�ȭ �Ǿ����ϴ�.");
				System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
				System.out.println("-------------------------------------");
				break;
			}
		}
	}
	
	//���ι��� "2-3.��ǰ�������� Ȯ��" �ڳ� ���� �޼ҵ�
	public static void printGoodsManagementList() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[��ǰ �������� Ȯ��]\n");
		File file = new File("��ǰ ��������.txt");
		Scanner fs = new Scanner(file);
		while(fs.hasNextLine()){
			String str = fs.nextLine();
			System.out.println(str);
		}
		fs.close();
		
		System.out.print("\n0.���� ȭ��: ");
		int select = input.nextInt();
		if(select==0)
			System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
		System.out.println("-------------------------------------");
	}
}
