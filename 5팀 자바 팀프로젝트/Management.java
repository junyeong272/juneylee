
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class Management{
	private static String codeNo; //상품 코드번호
	private static String gName; //상품명
	private static int uPrice; //가격
	private static int count; //수량
	private static int total_sales; //총 매출액
	private static String time; //시간
	private static String consumer; //소비자 이름
	private static String[] txtName={"담배","과자","음료수","라면","냉동식품","아이스크림","생필품","상품 품목"}; //카테고리 텍스트파일 이름
	public static Scanner input = new Scanner(System.in);
	
	public Management(){
	}
	
	//파일 덮어쓰기 메소드(변경된 정보를 txt파일에 덮어씀) 
	public static void overWrite(String txtN,Category o) throws IOException{
		FileWriter fw = new FileWriter(txtN+".txt");
		for(int i=0;i<o.array.size();i++)
			fw.write(o.array.get(i)+"\n"); //텍스트 파일에 추가한 상품까지 들어가도록 최신화
		fw.flush();
		fw.close();
	}
	
	//메인문의 "2-1-1.상품 품목 확인" 코너 실행 메소드
	public static void printGoodsCheck() throws IOException{	
		//"상품 품목" 텍스트 파일에 모든 카테고리 상품들 저장시키기
		FileWriter fw = new FileWriter("상품 품목.txt");
		for(int i=0;i<txtName.length-1;i++){
			Category goods = new Category(txtName[i]);
			for(int j=0;j<goods.codeNo.size();j++)
				fw.write(goods.toString(j)+"\n");
			
		}
		fw.flush();
		fw.close();	
		
		//상품 품목 확인 코너 실행
		System.out.println("-------------------------------------");
		System.out.println("[상품 품목 확인]");
		System.out.println("\n0.이전 화면으로\n1.담배  2.과자  3.음료수  4.라면 5.냉동식품\n6.아이스크림  7.생필품  8.전체보기");
		System.out.print("원하는 번호를 선택하세요 : ");
		int n = input.nextInt();
		if(n!=0){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" 코너 현황]");
			System.out.format("%-7s%-10s%-7s%-7s\n","코드번호","상품명","단가(원)","수량(개)");
			for(int i=0;i<goods.codeNo.size();i++)
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));			
			while(true){
				System.out.println("\n0.이전 화면으로");
				System.out.print("선택 : ");
				int select = input.nextInt();
				if(select==0){
					System.out.println("\n->이전 화면으로 돌아갑니다.");
					break;
				}
			}
		}
	}
	
	//메인문의 "2-1-2.상품 추가" 코너 실행 메소드
	public static void printGoodsAdd() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[상품 추가]");
		System.out.println("\n0.이전 화면으로\n1.담배  2.과자  3.음료수  4.라면\n5.냉동식품  6.아이스크림  7.생필품");
		System.out.print("추가할 상품의 카테고리를 선택하세요:");
		int n = input.nextInt();
		if(n!=0&&n!=8){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" 코너 현황]");
			System.out.format("%-7s%-10s%-7s%-7s\n","코드번호","상품명","단가(원)","수량(개)");
			for(int i=0;i<goods.codeNo.size();i++)
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));	
			System.out.print("\n추가할 상품 코드번호:");
			codeNo = input.next();
			if(goods.codeNo.contains(codeNo)){ //상품을 추가하기 위해 입력한 코드번호가 이미 존재하면 중복되지 않도록 설정
				System.out.println("\n이미 존재하는 코드번호 입니다.");
				System.out.println("->이전 화면으로 돌아갑니다.");
			}else{
				System.out.print("추가할 상품 상품명: ");
				gName = input.next();
				if(goods.gName.contains(gName)){ //추가하려는 상품이 이미 존재하는 상품이면 중복되지 않도록 설정
					System.out.println("\n이미 존재하는 상품명 입니다.");
					System.out.println("->이전 화면으로 돌아갑니다.");
				}else{
					System.out.print("추가할 상품 가격: ");
					uPrice = input.nextInt();
					System.out.print("추가할 상품 수량: ");
					count = input.nextInt();
				
					//물품 관리내역 txt파일에 정보를 저장
					FileWriter fw = new FileWriter("물품 관리내역.txt",true);
					fw.write(Cart.getRunTime()+" 상품 추가 => 상품정보: "+codeNo.toUpperCase()+" "+gName+" "+uPrice+"원 "+count+"개\n");
					fw.flush();
					fw.close();
			
					//입력받은 정보를 이용하여 해당 텍스트 파일에 상품을 추가함
					goods.array.add(codeNo.toUpperCase()+" "+gName+" "+uPrice+" "+count); //새로운 상품 추가
					Collections.sort(goods.array); //코드번호 정렬
		
					overWrite(txtName[n-1],goods);//변경된 정보를 txt파일에 덮어씀	
			
					System.out.println("\n상품이 추가 되었습니다.");
					System.out.println("->이전 화면으로 돌아갑니다.");
				}
			}
		}
			
	}

	//메인문의 "2-1-3.상품 제거" 코너 실행 메소드
	public static void printDeleteGoods() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[상품 제거]");
		System.out.println("\n0.이전 화면으로\n1.담배  2.과자  3.음료수  4.라면\n5.냉동식품  6.아이스크림  7.생필품");
		System.out.print("제거할 상품의 카테고리를 선택하세요:");
		int n = input.nextInt();
		if(n!=0&&n!=8){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" 코너 현황]");
			System.out.format("%-7s%-10s%-7s%-7s\n","코드번호","상품명","단가(원)","수량(개)");
			for(int i=0;i<goods.codeNo.size();i++){
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));
			}
			System.out.print("\n제거할 상품 코드번호:");
			codeNo = input.next();
			
			if(goods.codeNo.contains(codeNo)){
				//물품 관리내역 txt파일에 정보를 저장
				FileWriter fw = new FileWriter("물품 관리내역.txt",true);
				fw.write(Cart.getRunTime()+" 상품 제거 => 제거한 상품의 코드번호: "+codeNo.toUpperCase()+"\n");
				fw.flush();
				fw.close();
			
				//입력받은 코드번호를 이용하여 해당 상품을 제거함
				for(int i=0;i<goods.codeNo.size();i++){
					if(goods.codeNo.get(i).equalsIgnoreCase(codeNo)){ //코드번호 대소문자 구별없이 비교
						goods.array.remove(i);
						goods.codeNo.remove(i);
						break;
					}			
				}
				overWrite(txtName[n-1],goods);//변경된 정보를 txt파일에 덮어씀
				System.out.println("\n상품이 제거 되었습니다.");
				System.out.println("->이전 화면으로 돌아갑니다.");
			}else
				System.out.println("해당하는 코드번호가 없습니다.\n->이전 화면으로 돌아갑니다.");
				
		}
	}
	
	//메인문의 "2-1-4.상품 수량 추가" 코너 실행 메소드
	public static void printCountAdd() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[상품 수량 추가]");
		System.out.println("\n0.이전 화면으로\n1.담배  2.과자  3.음료수  4.라면\n5.냉동식품  6.아이스크림  7.생필품");
		System.out.print("수량을 추가할 상품의 카테고리를 선택하세요:");
		int n = input.nextInt();
		if(n!=0){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" 코너 현황]");
			System.out.format("%-7s%-10s%-7s%-7s\n","코드번호","상품명","단가(원)","수량(개)");
			for(int i=0;i<goods.codeNo.size();i++){
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));
			}
			System.out.print("\n수량을 추가할 상품 코드번호:");
			codeNo = input.next();
			if(goods.codeNo.contains(codeNo)){
				System.out.print("추가할 수량 설정(현재수량 "+goods.count.get(goods.codeNo.indexOf(codeNo))+"):");
				count = input.nextInt();
		
				//물품 관리내역 txt파일에 정보를 저장
				FileWriter fw = new FileWriter("물품 관리내역.txt",true);
				fw.write(Cart.getRunTime()+" 상품 수량 추가 => 코드번호: "+codeNo.toUpperCase()+", 추가한 수량: "+count+"\n");
				fw.flush();
				fw.close();
			
				//입력받은 코드번호를 이용하여 해당 텍스트 파일에 수량을 추가함
				for(int i=0;i<goods.codeNo.size();i++){
					if(goods.codeNo.get(i).equalsIgnoreCase(codeNo)){//코드번호 대소문자 구별없이 비교
						int newCount = goods.count.get(i)+count;
						if(newCount<0) //만약 변경된 수량이 0보다 작아지면 수량을 0으로 설정 (수량이 0보다 작을 수 없기 때문)
							newCount=0;
						goods.count.set(i,newCount); //입력받은 코드번호에 해당하는 상품의 수량에 입력받은 추가수량을 더해줌
						goods.array.set(i,goods.codeNo.get(i)+" "+goods.gName.get(i)+" "+goods.uPrice.get(i)+" "+goods.count.get(i)); //array 최신화
					}
				}
				overWrite(txtName[n-1],goods);//변경된 정보를 txt파일에 덮어씀
		
				System.out.println("\n수량이 변경 되었습니다.");
				System.out.println("->이전 화면으로 돌아갑니다.");
			}else
				System.out.println("해당하는 코드번호가 없습니다.\n->이전 화면으로 돌아갑니다.");
			
		}
	}
	
	//메인문의 "2-1-5.상품 가격 변경" 코너 실행 메소드
	public static void printuPriceChange() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[상품 가격 변경]");
		System.out.println("\n0.이전 화면으로\n1.담배  2.과자  3.음료수  4.라면\n5.냉동식품  6.아이스크림  7.생필품");
		System.out.print("가격을 변경할 상품의 카테고리를 선택하세요:");
		int n = input.nextInt();
		if(n!=0){
			Category goods = new Category(txtName[n-1]);
			System.out.println("\n["+txtName[n-1]+" 코너 현황]");
			System.out.format("%-7s%-10s%-7s%-7s\n","코드번호","상품명","단가(원)","수량(개)");
			for(int i=0;i<goods.codeNo.size();i++){
				System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));
			}
			System.out.print("\n가격을 변경할 상품 코드번호:");
			codeNo = input.next();
			if(goods.codeNo.contains(codeNo)){
				System.out.print("변경할 가격 설정:");
				uPrice = input.nextInt();
		
				//입력받은 코드번호를 이용하여 해당 텍스트 파일에 가격을 변경함
				for(int i=0;i<goods.codeNo.size();i++){
					if(goods.codeNo.get(i).equalsIgnoreCase(codeNo)){//코드번호 대소문자 구별없이 비교
					
						//물품 관리내역 txt파일에 정보를 저장
						FileWriter fw = new FileWriter("물품 관리내역.txt",true);
						fw.write(Cart.getRunTime()+" 상품 가격 변경 => 코드번호: "+goods.codeNo.get(i)+", 변경 가격: "+uPrice+"\n");
						fw.flush();
						fw.close();
					
						goods.uPrice.set(i,uPrice); //입력받은 코드번호에 해당하는 상품의 가격을 사용자에게 입력받은 가격으로 수정
						goods.array.set(i,goods.codeNo.get(i)+" "+goods.gName.get(i)+" "+goods.uPrice.get(i)+" "+goods.count.get(i)); //array 최신화
					}
				}
				overWrite(txtName[n-1],goods);//변경된 정보를 txt파일에 덮어씀
				System.out.println("\n가격이 변경 되었습니다.");
				System.out.println("->이전 화면으로 돌아갑니다.");
			}else
				System.out.println("해당하는 코드번호가 없습니다.\n->이전 화면으로 돌아갑니다.");
		}
	}
	
	//메인문의 "2-2.구매내역 및 매출 확인" 코너 실행 메소드
	public static void printConfirmSales() throws Exception{
		System.out.println("-------------------------------------");
		System.out.println("[구매내역 및 매출 확인]");
		Cart salesList = new Cart("구매내역");
		total_sales=0;
		System.out.format("%-5s%-7s%-10s%-7s%-7s%-10s%s\n","구매자","코드번호","상품명","단가(원)","수량(개)","합계(원)","구매 시간");
		for(int i=0;i<salesList.codeNo.size();i++){
			System.out.format("%-5s%-10s%-6s\t%7d%10d%10d%20s\n",salesList.consumer.get(i),salesList.codeNo.get(i),salesList.gName.get(i),salesList.uPrice.get(i),salesList.count.get(i),salesList.total.get(i),salesList.time.get(i));
			total_sales += salesList.total.get(i);
		}
		System.out.println("총 매출액: "+total_sales+"원");
		while(true){
			System.out.println("\n0.이전 화면으로\n1.구매내역 초기화");
			System.out.print("선택 : ");
			int select = input.nextInt();
			if(select==0){
				System.out.println("\n->이전 화면으로 돌아갑니다.");
				System.out.println("-------------------------------------");
				break;
			}else if(select==1){
				Cart.resetTxt("구매내역");
				System.out.println("구매내역이 초기화 되었습니다.");
				System.out.println("\n->이전 화면으로 돌아갑니다.");
				System.out.println("-------------------------------------");
				break;
			}
		}
	}
	
	//메인문의 "2-3.물품관리내역 확인" 코너 실행 메소드
	public static void printGoodsManagementList() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[물품 관리내역 확인]\n");
		File file = new File("물품 관리내역.txt");
		Scanner fs = new Scanner(file);
		while(fs.hasNextLine()){
			String str = fs.nextLine();
			System.out.println(str);
		}
		fs.close();
		
		System.out.print("\n0.이전 화면: ");
		int select = input.nextInt();
		if(select==0)
			System.out.println("\n->이전 화면으로 돌아갑니다.");
		System.out.println("-------------------------------------");
	}
}
