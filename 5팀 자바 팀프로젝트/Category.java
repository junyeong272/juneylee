
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
	private static String[] txtName={"담배","과자","음료수","라면","냉동식품","아이스크림","생필품"}; //카테고리 텍스트파일 이름
	static Scanner input = new Scanner(System.in);
	
	public Category(String txtName) throws IOException{
		String fp = txtName+".txt";
		Scanner fs = new Scanner(new FileReader(fp));
		int i=0;
		while(fs.hasNextLine()){
			String a = fs.nextLine();
			String[] str = a.split(" ");
			codeNo.add(str[0]); //코드번호 저장
			gName.add(str[1]);  //상품명 저장
			uPrice.add(Integer.parseInt(str[2])); // 단가 저장
			count.add(Integer.parseInt(str[3])); // 수량 저장
			array.add(codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i));
			i++;

		}
		fs.close();
	}
	
	//txt파일 저장할 때 쓰는 양식
	public String toString(int i){
		return codeNo.get(i)+" "+gName.get(i)+" "+uPrice.get(i)+" "+count.get(i);
	}
	
	//메인문의 "1-1-1-1~7.상품 카테고리"의 코너 실행을 위한 메소드
	public static void printCategory(String txtName,String consumer) throws IOException{ //상품코너 실행메소드
		Category goods = new Category(txtName); 
		System.out.println("-------------------------------------");
		System.out.println("["+txtName+" 코너]\n");
		System.out.format("%-7s%-10s%-7s%-7s\n","코드번호","상품명","단가(원)","수량(개)");
		for(int i=0;i<goods.codeNo.size();i++)
			System.out.format("%-10s%-6s\t%7d%10d\n",goods.codeNo.get(i),goods.gName.get(i),goods.uPrice.get(i),goods.count.get(i));	
		FileWriter fw = new FileWriter("장바구니.txt",true);
		while(true){
			System.out.println("\n0.이전 화면으로  1.장바구니 담기 ");
			System.out.print("선택 : ");
			int select = input.nextInt();
			if(select!=1){
				System.out.println("\n->이전 화면으로 돌아갑니다.");
				System.out.println("-------------------------------------");
				break;
			}
			System.out.print("장바구니에 담을 상품의 코드번호를 입력하세요: ");
			String buyCodeNo = input.next();
			System.out.print("장바구니에 담을 상품의 개수를 선택하세요: ");
			int buyCount = input.nextInt();
			
			//장바구니 파일에 입력한 정보 저장하기 (입력한 수량만큼 바꿔서 넣음)
			for(int i=0;i<goods.codeNo.size();i++){
				if(goods.codeNo.get(i).equalsIgnoreCase(buyCodeNo)){ //입력받은 코드번호 대소문자 구별없이 비교
					if(goods.count.get(i)<buyCount){
						System.out.println("선택하신 상품의 수량이 부족합니다.");
						break;
					}
					int save = goods.count.get(i);
					goods.count.set(i, buyCount); //장바구니에 선택한 수량을 넣기 위해 잠시 수량을 변경
					fw.write(goods.toString(i)+"  "+Cart.getRunTime()+" "+consumer+"\n"); 
					goods.count.set(i,save); //상품의 개수를 다시 복구 
				}		
			}			
		}	
		fw.flush();
		fw.close();
	}
	
	//메인문의 "1-1-2.상품 검색" 코너 실행을 위한 메소드
	public static void printSearchGoods() throws IOException{
		System.out.println("-------------------------------------");
		System.out.println("[상품 검색]");
		while(true){
			System.out.print("\n검색할 상품명을 정확하게 입력하세요: ");
			String searchName = input.next();
			int i=0;
			boolean exit = true;
			while(exit){
				Category o = new Category(txtName[i]);
				for(int j=0;j<o.codeNo.size();j++){
					if(o.gName.get(j).equals(searchName)){
						System.out.println("\n["+txtName[i]+"] 코너에 있습니다.");
						System.out.format("%-7s%-10s%-7s%-7s\n","코드번호","상품명","단가(원)","수량(개)");
						System.out.format("%-10s%-6s\t%7d%10d\n",o.codeNo.get(j),o.gName.get(j),o.uPrice.get(j),o.count.get(j));
						exit = false;
						break;
					}
				}
				if(exit==true&&i==txtName.length-1){
					System.out.println("죄송합니다! 검색하신 상품이 매장에 존재하지 않습니다.");
					exit=false;
					continue;
				}
				i++;
			}
		
			System.out.println("\n0.이전 화면으로\t1.재검색 ");
			System.out.print("선택 : ");
			int select = input.nextInt();
			if(select==0){
				System.out.println("\n->이전 화면으로 돌아갑니다.");
				System.out.println("-------------------------------------");
				break;
			}else if(select==1)
				continue;
		}
	}
	
}
