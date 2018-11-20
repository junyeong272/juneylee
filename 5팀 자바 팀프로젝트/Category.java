
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;

class Category{
	public ArrayList<String> array = new ArrayList<>();
	public ArrayList<String> codeNo = new ArrayList<>();
	public ArrayList<String> gName = new ArrayList<>();
	public ArrayList<Integer> uPrice = new ArrayList<>();
	public ArrayList<Integer> count = new ArrayList<>();
	private static String[] txtName={"���","����","�����","���","�õ���ǰ","���̽�ũ��","����ǰ"}; //ī�װ� �ؽ�Ʈ���� �̸�
	static Scanner input = new Scanner(System.in);
	
	public Category(String txtName) throws IOException{
		String fp = txtName+".txt";
		Scanner fs = new Scanner(new FileReader(fp));
		int i=0;
		while(fs.hasNextLine()){
			String a = fs.nextLine();
			String[] str = a.split(" ");
			codeNo.add(str[0]); //�ڵ��ȣ ����
			gName.add(str[1]);  //��ǰ�� ����
			uPrice.add(Integer.parseInt(str[2])); // �ܰ� ����
			count.add(Integer.parseInt(str[3])); // ���� ����
			array.add(codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i));
			i++;

		}
		fs.close();
	}
	
	//txt���� ������ �� ���� ���
	public String toString(int i){
		return codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i);
	}
	
	//���ι��� "1-1-1-1~7.��ǰ ī�װ�"�� �ڳ� ������ ���� �޼ҵ�
	public static void printCategory(String txtName,String consumer) throws IOException{ //��ǰ�ڳ� ����޼ҵ�
		Category goods = new Category(txtName); 
		System.out.println("-------------------------------------");
		System.out.println("["+txtName+" �ڳ�]\n");
		System.out.format("%-7s%-10s%-7s%-7s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)");
		for(int i=0;i<goods.codeNo.size();i++)
			System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));	
		FileWriter fw = new FileWriter("��ٱ���.txt",true);
		while(true){
			System.out.println("\n0.���� ȭ������  1.��ٱ��� ��� ");
			System.out.print("���� : ");
			int select = input.nextInt();
			if(select!=1){
				System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
				System.out.println("-------------------------------------");
				break;
			}
			System.out.print("��ٱ��Ͽ� ���� ��ǰ�� �ڵ��ȣ�� �Է��ϼ���: ");
			String buyCodeNo = input.next();
			System.out.print("��ٱ��Ͽ� ���� ��ǰ�� ������ �����ϼ���: ");
			int buyCount = input.nextInt();
			
			//��ٱ��� ���Ͽ� �Է��� ���� �����ϱ� (�Է��� ������ŭ �ٲ㼭 ����)
			for(int i=0;i<goods.codeNo.size();i++){
				if(goods.codeNo.get(i).equalsIgnoreCase(buyCodeNo)){ //�Է¹��� �ڵ��ȣ ��ҹ��� �������� ��
					if(goods.count.get(i)<buyCount){
						System.out.println("�����Ͻ� ��ǰ�� ������ �����մϴ�.");
						break;
					}
					int save = goods.count.get(i);
					goods.count.set(i, buyCount); //��ٱ��Ͽ� ������ ������ �ֱ� ���� ��� ������ ����
					fw.write(goods.toString(i)+"  "+Cart.getRunTime()+" "+consumer+"\n"); 
					goods.count.set(i,save); //��ǰ�� ������ �ٽ� ���� 
				}		
			}			
		}	
		fw.flush();
		fw.close();
	}
	
	//���ι��� "1-1-2.��ǰ �˻�" �ڳ� ������ ���� �޼ҵ�
	public static void printSearchGoods() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[��ǰ �˻�]");
		while(true){
			System.out.print("\n�˻��� ��ǰ���� ��Ȯ�ϰ� �Է��ϼ���: ");
			String searchName = input.next();
			int i=0;
			boolean exit = true;
			while(exit){
				Category o = new Category(txtName[i]);
				for(int j=0;j<o.codeNo.size();j++){
					if(o.gName.get(j).equals(searchName)){
						System.out.println("\n["+txtName[i]+"] �ڳʿ� �ֽ��ϴ�.");
						System.out.format("%-7s%-10s%-7s%-7s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)");
						System.out.format("%-10s%-6s\t%7d%10d\n",o.codeNo.get(j),o.gName.get(j),o.uPrice.get(j),o.count.get(j));
						exit = false;
						break;
					}
				}
				if(exit==true&&i==txtName.length-1){
					System.out.println("�˼��մϴ�! �˻��Ͻ� ��ǰ�� ���忡 �������� �ʽ��ϴ�.");
					exit=false;
					continue;
				}
				i++;
			}
		
			System.out.println("\n0.���� ȭ������\t1.��˻� ");
			System.out.print("���� : ");
			int select = input.nextInt();
			if(select==0){
				System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
				System.out.println("-------------------------------------");
				break;
			}else if(select==1)
				continue;
		}
	}
	
}
