
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Cart{
	public ArrayList<String> array = new ArrayList<>();
	public ArrayList<String> codeNo = new ArrayList<>();
	public ArrayList<String> gName = new ArrayList<>();
	public ArrayList<Integer> uPrice = new ArrayList<>();
	public ArrayList<Integer> count = new ArrayList<>();
	public ArrayList<Integer> total = new ArrayList<>();	
	public ArrayList<String> time = new ArrayList<>();
	public ArrayList<String> consumer = new ArrayList<>();
	public static Scanner input = new Scanner(System.in);
	
	public Cart() throws IOException{ // �⺻�� ��ٱ��� ���Ͽ� �ִ� �������� �о �ʵ忡 ����
		File fp = new File("��ٱ���.txt");
		Scanner fs = new Scanner(fp);
		int i=0;
		while(fs.hasNextLine()){
			String a = fs.nextLine();
			String[] str = a.split(" ");
			codeNo.add(str[0]); //�ڵ��ȣ ����
			gName.add(str[1]);  //��ǰ�� ����
			uPrice.add(Integer.parseInt(str[2])); // �ܰ� ����
			count.add(Integer.parseInt(str[3])); // ���� ����
			total.add(uPrice.get(i)*count.get(i)); //�հ� ����
			time.add(str[5]);//��ٱ��Ͽ� ���� �ð� ����
			consumer.add(str[6]);
			array.add(codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i)+" "+total.get(i)+" "+time.get(i)+" "+consumer.get(i));
			i++;
		}
		fs.close();
	}
	
	public Cart(String txtName) throws IOException{ // txtName ���Ͽ� �ִ� �������� �о �ʵ忡 ����("���� ����" Ȱ��)
		File fp = new File(txtName+".txt");
		Scanner fs = new Scanner(fp);
		int i=0;
		while(fs.hasNextLine()){
			String a = fs.nextLine();
			String[] str = a.split(" ");
			codeNo.add(str[0]); //�ڵ��ȣ ����
			gName.add(str[1]);  //��ǰ�� ����
			uPrice.add(Integer.parseInt(str[2])); // �ܰ� ����
			count.add(Integer.parseInt(str[3])); // ���� ����
			total.add(uPrice.get(i)*count.get(i)); //�հ� ����
			time.add(str[5]);//��ٱ��Ͽ� ���� �ð� ����
			consumer.add(str[6]);
			array.add(codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i)+" "+total.get(i)+" "+time.get(i)+" "+consumer.get(i));
			i++;
		}
		fs.close();
	}
	
	
	
	//txt���� ������ �� ���� ���
	public String toString(int i){
		return codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i)+" "+total.get(i)+" "+time.get(i)+" "+consumer.get(i);
	}
	
	//��ٱ��� Ư�� ǰ�� �����ϴ� �޼ҵ�
	public static void deleteCart() throws IOException{
		Cart cart = new Cart();
		System.out.print("\n������ ��ǰ�� �ڵ��ȣ�� �Է��ϼ���:");
		String deleteCodeNo = input.next();
		for(int i=0;i<cart.codeNo.size();i++){
			if(cart.codeNo.get(i).equalsIgnoreCase(deleteCodeNo)){//�ڵ��ȣ ��ҹ��� ���о��� ��
				cart.array.remove(i);
				cart.codeNo.remove(i);
				i=0;
			}			
		}
		FileWriter fw = new FileWriter("��ٱ���.txt");
		for(int i=0;i<cart.array.size();i++){
			fw.write(cart.array.get(i)+"\n");
		}
		fw.flush();
		fw.close();	
	}
	
	//Ư�� �ؽ�Ʈ������ ������ �ʱ�ȭ
	public static void resetTxt(String txtName) throws IOException{
		FileWriter fw = new FileWriter(txtName+".txt");
		fw.write("");
		fw.flush();
		fw.close();
	}
	
	//Ư�� �Һ����� ��ٱ��� �ʱ�ȭ�ϴ� �޼ҵ�
	public static void resetCart(String consumer) throws IOException{	
		Cart cart = new Cart();
		int j=0;
		do{
			if(cart.consumer.get(j).equals(consumer)){
				cart.array.remove(j);
				cart.consumer.remove(j);
				j=0;
				continue;
			}
			j++;
		}while(j<cart.consumer.size());
		
		FileWriter fw = new FileWriter("��ٱ���.txt");
		for(int i=0;i<cart.array.size();i++){
			fw.write(cart.array.get(i)+"\n");
		}
		fw.flush();
		fw.close();
	}
	
	//��ǰ ������ ������ŭ ��ǰ�� ���� �ٿ��ִ� �޼ҵ�
	public static void attractCount_afterBuy() throws IOException{
		String[] txtName={"���","����","�����","���","�õ���ǰ","���̽�ũ��","����ǰ"};
		Cart cart = new Cart();
		for(int i=0;i<txtName.length;i++){
			Category goods = new Category(txtName[i]);
			FileWriter fw = new FileWriter(txtName[i]+".txt");
			for(int j=0;j<goods.codeNo.size();j++){
				for(int k=0;k<cart.codeNo.size();k++){
					if(goods.codeNo.get(j).equals(cart.codeNo.get(k))){
						int newCount = goods.count.get(j)-cart.count.get(k);	
						goods.count.set(j, newCount);
					}
				}
				fw.write(goods.toString(j)+"\n");
			}
			fw.flush();
			fw.close();	
		}
	}
	
	//����ð�(�Ǵ� ����ð�)�� yy-MM-dd[HH:mm:ss]���·� �����ؼ� �������ִ� �޼ҵ�
	public static String getRunTime(){
		SimpleDateFormat setDate= new SimpleDateFormat("yy-MM-dd[HH:mm:ss]");
		Date currentTime = new Date();
		String runTime = setDate.format(currentTime);
		return runTime;
	}
	
	//���ι��� "1-1-3.��ٱ��� �� ����" �ڳ� ������ ���� �޼ҵ� (consumer:�α��ε� �Һ��� �̸�)
	public static void printCart(String consumer) throws IOException{ 
		Cart cart = new Cart();
		int sum =0;
		System.out.println("-------------------------------------");
		System.out.println("[��ٱ���]\n");
		System.out.format("%-7s%-10s%-7s%-7s%-12s%s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)","�հ�(��)","���� �ð�");
		for(int i=0;i<cart.codeNo.size();i++){
			if(cart.consumer.get(i).equals(consumer)){ //�α��ε� �ش� �Һ����� ��ٱ��ϸ� ������
				System.out.format("%-10s%-6s\t%7d%10d%10d%22s\n",cart.codeNo.get(i),cart.gName.get(i),cart.uPrice.get(i),cart.count.get(i),cart.total.get(i),cart.time.get(i));
				sum += cart.total.get(i);
			}
		}
		System.out.println("�� ����:"+sum+"��");
		System.out.println("\n0.���� ȭ������\n1.��ٱ��� ����\n2.��ǰ �����ϱ�\n3.��ǰ �����ϱ�");
		System.out.print("���ϴ� ������ �����ϼ���:");
		int choice = input.nextInt();
		switch(choice){
		case 0: //���� ȭ������
			System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
			break;
		case 1: //1-1-3-1.��ٱ��� ����
			resetCart(consumer);
			System.out.println("\n��ٱ��ϸ� ������ϴ�.");
			System.out.println("->���� ȭ������ ���ư��ϴ�.");
			break;
		case 2: //1-1-3-2.��ǰ �����ϱ�
			deleteCart();
			System.out.println("\n�����Ͻ� ��ǰ�� ��ٱ��Ͽ��� �����߽��ϴ�.");
			System.out.println("->���� ȭ������ ���ư��ϴ�.");
			break;
		case 3: //1-1-3-3.��ǰ �����ϱ�
			System.out.println("\n��ǰ�� �����Ͽ����ϴ�.");
			System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
			
			//���ų��� ���Ͽ� ������ ���� �����ϱ�
			FileWriter fw = new FileWriter("���ų���.txt",true);
			for(int i=0;i<cart.codeNo.size();i++){
				cart.time.set(i,getRunTime()); //�����ϴ� ������ �ð����� �ð� ����
				fw.write(cart.toString(i)+"\n"); 		
			}
			fw.flush();
			fw.close();
			
			//���ſϷ��ϸ� ������ ��ǰ�� ������ŭ �Ǹ��ϴ� ��ǰ�� ���� ���̱�
			attractCount_afterBuy();
			
			//���ſϷ��ϸ� ��ٱ��� �ʱ�ȭ�ϱ�
			resetCart(consumer);
			break;
		}
	}
	
	//���ι��� "1-1-4.���ų���" �ڳ� ������ ���� �޼ҵ�   (consumer:�α��ε� �Һ��� �̸�)
	public static void printPurchaseList(String consumer) throws Exception{ 
		Cart purchase = new Cart("���ų���");
		int sum =0;
		System.out.println("-------------------------------------");
		System.out.println("[���ų���]\n");
		System.out.format("%-7s%-10s%-7s%-7s%-12s%s\n","�ڵ��ȣ","��ǰ��","�ܰ�(��)","����(��)","�հ�(��)","���� �ð�");
		for(int i=0;i<purchase.codeNo.size();i++){
			if(purchase.consumer.get(i).equals(consumer)){ //�α��ε� �ش� �Һ����� ���ų����� ������
				System.out.format("%-10s%-6s\t%7d%10d%10d%22s\n",purchase.codeNo.get(i),purchase.gName.get(i),purchase.uPrice.get(i),purchase.count.get(i),purchase.total.get(i),purchase.time.get(i));
				sum += purchase.total.get(i);
			}
		}
	
		System.out.println("�� ���� ����:"+sum+"��");
		System.out.print("\n0.���� ȭ��: ");
		int choice = input.nextInt();
		if(choice==0){
			System.out.println("\n->���� ȭ������ ���ư��ϴ�.");
			System.out.println("-------------------------------------");
		}
		
	}
		
}
