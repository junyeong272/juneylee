
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
	
	public Cart() throws IOException{ // 기본은 장바구니 파일에 있는 정보들을 읽어서 필드에 저장
		File fp = new File("장바구니.txt");
		Scanner fs = new Scanner(fp);
		int i=0;
		while(fs.hasNextLine()){
			String a = fs.nextLine();
			String[] str = a.split(" ");
			codeNo.add(str[0]); //코드번호 저장
			gName.add(str[1]);  //상품명 저장
			uPrice.add(Integer.parseInt(str[2])); // 단가 저장
			count.add(Integer.parseInt(str[3])); // 수량 저장
			total.add(uPrice.get(i)*count.get(i)); //합계 저장
			time.add(str[5]);//장바구니에 넣은 시간 저장
			consumer.add(str[6]);
			array.add(codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i)+" "+total.get(i)+" "+time.get(i)+" "+consumer.get(i));
			i++;
		}
		fs.close();
	}
	
	public Cart(String txtName) throws IOException{ // txtName 파일에 있는 정보들을 읽어서 필드에 저장("구매 내역" 활용)
		File fp = new File(txtName+".txt");
		Scanner fs = new Scanner(fp);
		int i=0;
		while(fs.hasNextLine()){
			String a = fs.nextLine();
			String[] str = a.split(" ");
			codeNo.add(str[0]); //코드번호 저장
			gName.add(str[1]);  //상품명 저장
			uPrice.add(Integer.parseInt(str[2])); // 단가 저장
			count.add(Integer.parseInt(str[3])); // 수량 저장
			total.add(uPrice.get(i)*count.get(i)); //합계 저장
			time.add(str[5]);//장바구니에 넣은 시간 저장
			consumer.add(str[6]);
			array.add(codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i)+" "+total.get(i)+" "+time.get(i)+" "+consumer.get(i));
			i++;
		}
		fs.close();
	}
	
	
	
	//txt파일 저장할 때 쓰는 양식
	public String toString(int i){
		return codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i)+" "+total.get(i)+" "+time.get(i)+" "+consumer.get(i);
	}
	
	//장바구니 특정 품목 삭제하는 메소드
	public static void deleteCart() throws IOException{
		Cart cart = new Cart();
		System.out.print("\n제거할 물품의 코드번호를 입력하세요:");
		String deleteCodeNo = input.next();
		for(int i=0;i<cart.codeNo.size();i++){
			if(cart.codeNo.get(i).equalsIgnoreCase(deleteCodeNo)){//코드번호 대소문자 구분없이 비교
				cart.array.remove(i);
				cart.codeNo.remove(i);
				i=0;
			}			
		}
		FileWriter fw = new FileWriter("장바구니.txt");
		for(int i=0;i<cart.array.size();i++){
			fw.write(cart.array.get(i)+"\n");
		}
		fw.flush();
		fw.close();	
	}
	
	//특정 텍스트파일의 내용을 초기화
	public static void resetTxt(String txtName) throws IOException{
		FileWriter fw = new FileWriter(txtName+".txt");
		fw.write("");
		fw.flush();
		fw.close();
	}
	
	//특정 소비자의 장바구니 초기화하는 메소드
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
		
		FileWriter fw = new FileWriter("장바구니.txt");
		for(int i=0;i<cart.array.size();i++){
			fw.write(cart.array.get(i)+"\n");
		}
		fw.flush();
		fw.close();
	}
	
	//물품 구매한 수량만큼 물품의 개수 줄여주는 메소드
	public static void attractCount_afterBuy() throws IOException{
		String[] txtName={"담배","과자","음료수","라면","냉동식품","아이스크림","생필품"};
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
	
	//현재시간(또는 실행시간)을 yy-MM-dd[HH:mm:ss]형태로 저장해서 리턴해주는 메소드
	public static String getRunTime(){
		SimpleDateFormat setDate= new SimpleDateFormat("yy-MM-dd[HH:mm:ss]");
		Date currentTime = new Date();
		String runTime = setDate.format(currentTime);
		return runTime;
	}
	
	//메인문의 "1-1-3.장바구니 및 구매" 코너 실행을 위한 메소드 (consumer:로그인된 소비자 이름)
	public static void printCart(String consumer) throws IOException{ 
		Cart cart = new Cart();
		int sum =0;
		System.out.println("-------------------------------------");
		System.out.println("[장바구니]\n");
		System.out.format("%-7s%-10s%-7s%-7s%-12s%s\n","코드번호","상품명","단가(원)","수량(개)","합계(원)","담은 시간");
		for(int i=0;i<cart.codeNo.size();i++){
			if(cart.consumer.get(i).equals(consumer)){ //로그인된 해당 소비자의 장바구니만 보여줌
				System.out.format("%-10s%-6s\t%7d%10d%10d%22s\n",cart.codeNo.get(i),cart.gName.get(i),cart.uPrice.get(i),cart.count.get(i),cart.total.get(i),cart.time.get(i));
				sum += cart.total.get(i);
			}
		}
		System.out.println("총 가격:"+sum+"원");
		System.out.println("\n0.이전 화면으로\n1.장바구니 비우기\n2.물품 제거하기\n3.물품 구매하기");
		System.out.print("원하는 실행을 선택하세요:");
		int choice = input.nextInt();
		switch(choice){
		case 0: //이전 화면으로
			System.out.println("\n->이전 화면으로 돌아갑니다.");
			break;
		case 1: //1-1-3-1.장바구니 비우기
			resetCart(consumer);
			System.out.println("\n장바구니를 비웠습니다.");
			System.out.println("->이전 화면으로 돌아갑니다.");
			break;
		case 2: //1-1-3-2.물품 제거하기
			deleteCart();
			System.out.println("\n선택하신 물품을 장바구니에서 삭제했습니다.");
			System.out.println("->이전 화면으로 돌아갑니다.");
			break;
		case 3: //1-1-3-3.물품 구매하기
			System.out.println("\n물품을 구매하였습니다.");
			System.out.println("\n->이전 화면으로 돌아갑니다.");
			
			//구매내역 파일에 구매한 정보 저장하기
			FileWriter fw = new FileWriter("구매내역.txt",true);
			for(int i=0;i<cart.codeNo.size();i++){
				cart.time.set(i,getRunTime()); //구매하는 시점의 시간으로 시간 변경
				fw.write(cart.toString(i)+"\n"); 		
			}
			fw.flush();
			fw.close();
			
			//구매완료하면 구매한 물품의 수량만큼 판매하는 물품의 수량 줄이기
			attractCount_afterBuy();
			
			//구매완료하면 장바구니 초기화하기
			resetCart(consumer);
			break;
		}
	}
	
	//메인문의 "1-1-4.구매내역" 코너 실행을 위한 메소드   (consumer:로그인된 소비자 이름)
	public static void printPurchaseList(String consumer) throws Exception{ 
		Cart purchase = new Cart("구매내역");
		int sum =0;
		System.out.println("-------------------------------------");
		System.out.println("[구매내역]\n");
		System.out.format("%-7s%-10s%-7s%-7s%-12s%s\n","코드번호","상품명","단가(원)","수량(개)","합계(원)","구매 시간");
		for(int i=0;i<purchase.codeNo.size();i++){
			if(purchase.consumer.get(i).equals(consumer)){ //로그인된 해당 소비자의 구매내역만 보여줌
				System.out.format("%-10s%-6s\t%7d%10d%10d%22s\n",purchase.codeNo.get(i),purchase.gName.get(i),purchase.uPrice.get(i),purchase.count.get(i),purchase.total.get(i),purchase.time.get(i));
				sum += purchase.total.get(i);
			}
		}
	
		System.out.println("총 구매 가격:"+sum+"원");
		System.out.print("\n0.이전 화면: ");
		int choice = input.nextInt();
		if(choice==0){
			System.out.println("\n->이전 화면으로 돌아갑니다.");
			System.out.println("-------------------------------------");
		}
		
	}
		
}
