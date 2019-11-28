import turtle
import random
import time

class Player_Account :
    def __init__(self):
        self.Player_ID = ""
        self.Player_Coin = 0
    def setID(self):
        self.Player_ID = input("ID를 입력하세요: ")
    def setCoin(self):
        self.Player_Coin = int(input("시작 코인은 얼마입니까? "))
    def addCoin(self,value):
        self.Player_Coin = self.Player_Coin + value
    def subCoin(self,value):
        self.Player_Coin = self.Player_Coin - value
    def getID(self):
        return self.Player_ID
    def getCoin(self):
        return self.Player_Coin

class Dealer_Account(Player_Account):
    def __init__(self):
        self.Player_ID = "Dealer"
        self.Player_Coin = 500
    def subCoin(self):
        self.Player_Coin -= 50
        
def DrawCard(cardText,X,Y):
    t.penup()
    t.goto(X,Y)
    t.pendown()
    for i in range(2):
        t.forward(100)
        t.right(90)
        t.forward(150)
        t.right(90)
    t.penup()
    
    if cardText[1:4] == "다이아" or cardText[2:5] == "다이아":
        t.color("red")
    if cardText[0:2] == "10": 
        t.goto(X+80,Y-20)
        t.write("10",font=(10))
        t.goto(X+10,Y-150)
        t.write("10",font=(10))
    else:
        t.goto(X+80,Y-20)
        t.write(cardText[0],font=(10))
        t.goto(X+10,Y-150)
        t.write(cardText[0],font=(10))
    t.color("black")
    
    if cardText[1:4] == "클로버" or cardText[2:5] == "클로버":
        t.goto(X+30,Y-80)
        t.dot(40)
        t.goto(X+70,Y-80)
        t.dot(40)
        t.goto(X+50,Y-60)
        t.dot(40)
        t.goto(X+50,Y-70)
        t.setheading(180)
        t.begin_fill()
        t.circle(30, steps=3)
        t.end_fill()
        t.setheading(0)
    else:
        t.goto(X+20,Y-80)
        t.pendown()
        t.color("red")
        t.fillcolor("red")
        t.begin_fill()
        for i in range(3):
            t.forward(60)
            t.right(120)
        for i in range(3):
            t.forward(60)
            t.left(120)
        t.end_fill()
        t.color("black")

    if Y > 0 :
        mode = "D"
    else:
        mode = "P"
        
    if cardText[0:2] == "10":
        return 10,mode
    elif cardText[0] == "J" or cardText[0] =="Q" or cardText[0] == "K":
        return 10,mode
    elif cardText[0] == "A":
        return 11,mode
    else:
        return int(cardText[0]),mode

def cardSum(list):
    sum = 0
    for i in list:
        sum += i
    return sum

#----------------------------------------------------------------------------
player = Player_Account()
player.setID()
player.setCoin()
dealer = Dealer_Account()
t = turtle.Turtle()
count = 0

while True:
    count += 1
    print(count,"번째 게임을 시작합니다.(시작코인:",player.getCoin(),")")
    
    t.speed(0)
    t.hideturtle()
    t.penup()
    t.goto(400,300)
    t.write(player.getID(),font=(20))
    t.goto(400,280)
    t.write(player.getCoin(),font=(20))
    t.goto(-400,300)
    t.write(dealer.getID(),font=(20))
    t.goto(-400,280)
    t.write(dealer.getCoin(),font=(20))
    

    card = [
                ["A클로버","2클로버","3클로버","4클로버","5클로버","6클로버","7클로버","8클로버","9클로버","10클로버","J클로버","Q클로버","K클로버"],
                ["A다이아","2다이아","3다이아","4다이아","5다이아","6다이아","7다이아","8다이아","9다이아","10다이아","J다이아","Q다이아","K다이아"]
            ]

    card_pop = []
    for i in range(4):
        mark = random.randrange(0,2)
        out_card = random.randrange(0,len(card[mark]))
        card_pop.append(card[mark][out_card])
        card[mark].pop(out_card)

    Dealer_Card = []
    Player_Card = []
    
    x = [-300,-150,-300,-150,0,150,300]
    y = [200,200,-100,-100,-100,-100,-100]

    D_cnt = 0
    P_cnt = 0
    for i in range(4):
        a,b = DrawCard(card_pop[i],x[i],y[i])
        if b == "D":
            Dealer_Card.append(a)
            D_cnt += 1
        elif b == "P":
            Player_Card.append(a)
            P_cnt += 1
    
    start_coin = player.getCoin()
    player.subCoin(10)
    print("10코인이 차감되어 현재 코인은",player.getCoin(),"입니다.")
            
    Dealer_Sum = cardSum(Dealer_Card)
    Player_Sum = cardSum(Player_Card)
        
    for i in range(4,7):
        text = input("카드를 한장 더 받으시겠습니까?[hit/stand]: ")
        if text.lower() == "hit":
            if P_cnt == 2 and player.getCoin() >= 20:       #hit 1번
                player.subCoin(20)
                print("20코인이 차감되어 현재 코인은",player.getCoin(),"입니다.")
            elif P_cnt == 3 and player.getCoin() >= 40:     #hit 2번
                player.subCoin(40)
                print("40코인이 차감되어 현재 코인은",player.getCoin(),"입니다.")
            elif P_cnt == 4 and player.getCoin() >= 80:     #hit 3번
                player.subCoin(80)
                print("80코인이 차감되어 현재 코인은",player.getCoin(),"입니다.")
            else:
                print("코인이 부족해서 카드를 받을 수 없습니다.")
                break
            
            mark = random.randrange(0,2)
            out_card = random.randrange(0,len(card[mark]))
            card_pop.append(card[mark][out_card])
            card[mark].pop(out_card)
            
            a,b = DrawCard(card_pop[i], x[i], y[i])
            Player_Card.append(a)
            P_cnt += 1
                
            Player_Sum = cardSum(Player_Card)
            if Player_Sum > 21:
                if 11 in Player_Card:
                    a = Player_Card.index(11)
                    Player_Card[a] = 1
                    Player_Sum = cardSum(Player_Card)
                else:
                    text = "21초과"
                    break
        if text.lower() =="stand" or player.getCoin() == 0:
            break

    if text == "21초과":
        print("카드 숫자의 합이 21을 넘었습니다. 플레이어의 패배")
    else:
        for i in range(3):  #딜러도 최대 3장 더 뽑을 수 있음
            Player_Sum = cardSum(Player_Card)
            Dealer_Sum = cardSum(Dealer_Card)

            hit = []
            stand = []
            #딜러가 남은 카드를 확인하여 승산이 있는 경우에만 카드를 뽑는다. 
            #딜러가 자기 코인을 어느정도 고려하고 이길확률을 최대한 증가하도록 코딩
            #즉,딜러가 질 확률이 높으면 굳이 카드를 뽑지않음 (코인낭비 줄임)

            if dealer.getCoin() <= 0:   #딜러의 코인이 부족한 경우 stand
                stand.append(1)
            if Dealer_Sum == 21 or Player_Sum == 21:    #플레이어가 21인 경우 잘해봐야 무승부고 이길 수 없기 때문에 stand ->코인아낌
                stand.append(1)
            if Dealer_Sum > Player_Sum:     #딜러숫자가 높은 경우 stand
                stand.append(1)
            if Dealer_Sum >= 17 and 11 not in Dealer_Card:  #버스트될 확률이 높기 때문에 코인아끼고 stand 
                stand.append(1)
            if Dealer_Sum <= 11:    #버스트될 확률이 없기 때문에 무조건 hit
                hit.append(1)
            if 11 in Dealer_Card:
                if Dealer_Sum == Player_Sum or Dealer_Sum >= 19:    #새로 뽑아도 딜러가 이길확률이 낮기 때문에 stand
                    stand.append(1)
                else:
                    hit.append(1)
            if 12 <= Dealer_Sum <=16 and 11 not in Dealer_Card:
                counting = [1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,10,10,10,10,10,10]
                max = 21 - Dealer_Sum
                all_card = Dealer_Card + Player_Card
                for j in all_card:
                    if j == 11:
                        j = 1
                    counting.remove(j)
                num = 0     # num: 남은 카드 중에서 뽑아도 버스트 되지 않는 카드의 수 계산을 위한 변수
                for j in counting:  
                    if j <= max:
                        num += 1    
                prob = round(num*10/len(counting))
                # 버스트되지 않을 확률이(카드를 뽑아 21을 넘지 않을 확률) 0.45 이상일때 hit
                if prob >= 5:   
                    hit.append(1)
                else:
                    stand.append(1)

            if any(stand):
                break
            
            if any(hit):
                mark = random.randrange(0,2)
                out_card = random.randrange(0,len(card[mark]))
                card_pop.append(card[mark][out_card])
                card[mark].pop(out_card)
                
                a,b = DrawCard(card_pop[-1], x[i+4], 200)
                Dealer_Card.append(a)
                D_cnt += 1
                dealer.subCoin()
                Dealer_Sum = cardSum(Dealer_Card)

            #딜러카드 합이 21이 넘는 경우 A를 11에서 1로 바꿔주고 없으면 딜러카드 합을 0으로 설정
            if Dealer_Sum > 21:
                if 11 in Dealer_Card:
                    a = Dealer_Card.index(11)
                    Dealer_Card[a] = 1
                    Dealer_Sum = cardSum(Dealer_Card)
                else:
                    print("딜러카드 숫자의 합이 21을 넘었습니다.")
                    Dealer_Sum = 0
                    break
                
            if Dealer_Sum > Player_Sum:
                break

        print("딜러:",Dealer_Sum,", 플레이어:",Player_Sum)
        if Player_Sum == Dealer_Sum:
            print("무승부")
            if P_cnt == 2:          #hit 0번
                player.addCoin(10)
            elif P_cnt == 3:        #hit 1번
                player.addCoin(30)
            elif P_cnt == 4:        #hit 2번
                player.addCoin(70)
            elif P_cnt == 5:        #hit 3번
                player.addCoin(150)
        elif Player_Sum > Dealer_Sum:
            print("플레이어의 승리")
            if P_cnt == 2:          #hit 0번
                player.addCoin(20)
            elif P_cnt == 3:        #hit 1번
                player.addCoin(60)
            elif P_cnt == 4:        #hit 2번
                player.addCoin(140)
            elif P_cnt == 5:        #hit 3번
                player.addCoin(300)
        elif Player_Sum < Dealer_Sum:
            print("플레이어의 패배")

    end_coin = player.getCoin()
    result_coin = end_coin - start_coin        
    print("최종 코인은",player.getCoin(),"입니다. 게임 결과:",result_coin)
    if player.getCoin() <= 0:
        print("플레이어가 파산하였습니다.")
        print("-----게임 종료-----")
        break
    elif dealer.getCoin() <= 0:
        print("딜러의 코인이 부족합니다.")
        print("-----게임 종료-----")
        break
    
    time.sleep(2)
    print("-"*50)
    t.reset()



    
