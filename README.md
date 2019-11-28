# juneylee
현재까지 수강했던 SW과목 및 프로젝트 code 등이 있다. 

## **1.Data Structure(자료구조)**

자료구조는 Data의 저장 및 관리에 대한 SW분야의 가장 대표적인 과목으로 자료구조를 바탕으로 효율적인 데이터 저장 및 관리 알고리즘을 작성할 수 있게 된다. 

  ### 1)stack
  
  07_stack 폴더에 있으며 LIFO(후입선출)구조인 stack에 대한 c코드가 들어있다. 크게 push, pop으로 구성되어 있다.

  ### 2)queue
  
  08_queue 폴더에 있으며 FIFO(선입선출)구조인 queue에 관한 c코드가 들어있다. 자료를 넣는 enqueue와 자료를 빼내는 dequeue가 있다.

  ### 3)llist
  
  09_llist 폴더에 있으며 큐와 스택과 다르게 어느 위치든 자료를 넣고 뺄 수 있는 리스트이다.

  ### 4)tree
  
  10_tree 폴더에 있으며 트리구조로 자료를 찾는 면에서 보다 빠르게 찾을 수 있다.

  ### 5)avl
  
  11_avl_tree 폴더에 있으며 트리구조에서 높이 차이가 최대 1밖에 차이가 안나는 구조이다.

  ### 6)heap
  
  12_heap 폴더에 있다.


## **2.Operating System(운영체제)**

운영체제는 컴퓨터의 자원들을 효율적으로 관리하는 기술들에 대해 배우는 과목이다.

  ### 1)process
  
운영체제의 기본 작업단위인 프로세스에 대한 과제이다. 프로세스는 항상 자신을 생성하게 해주는 부모 프로세스가 존재하게 되고 이러한 구분은 pid라는    것으로 구분하게 된다. 이 과제를 통해 프로세스를 생성하는 법을 c언어를 통해 배우게 되고 부모 프로세스와 자식프로세스가 프로그램 수행간에 어떻게 동작하는지 알게 되었다.

  ### 2)thread
  
프로세스보다 더 작은 작업단위인 쓰레드에 대한 과제이다. 주어진 코드를 분석하여 프로세스와 쓰레드의 차이점을 알게 되고 쓰레드의 동작방법에 대해 알 수 있다.


## **3.Java**

java programming 수업을 들으면서 수행했던 Term project 파일들이 들어있다. 

Term Project로 소비자(고객)의 입장에서 가상으로 편의점을 이용하는데 필요한 기능들을 구현하여 편의점 이용/물건 구매를 할 수 있도록 만들고, 

관리자(점장)의 입장에서 편의점을 운용하는데 필요한 기능들을 구현해 물품들을 관리/운영 할 수 있도록 만드는 것을 목표로 코드를 구현함


## **4.파이선 프로그래밍**

파이선 프로그래밍의 Term project로 카드 게임인 블랙잭 게임을 만들었다. (컴퓨터와 사용자와의 대결)

블랙잭 게임의 기본 원리를 적용시켜 코드를 구현했고 

Turtle을 이용하여 상대방 카드, 자신의 카드, 점수 등을 그림으로 보여주어 게임을 진행할 수있도록만들었다.


## **5.SoC설계 및 프로그래밍**

학부 SoC설계 및 프로그래밍 수업을 들으며 텀프로젝트 수행 코드

-RPS-Z7202-TK 보드를 이용한 digital clock을 구현

-기존에 구현한 digital clcok에 인터럽트 IP를 구현하여 시간/날짜 설정 모드를 추가

-PWM제어 IP를 구현하고 GPIO핀에 피에조 부저를 연결해 PWM제어를 통해 음을 조절해 정시 알람 구현

-TEXT_LCD에 연/월/일 표시기능 추가


### 정리
보드의 PL영역에 베릴로그를 통해 PWM IP, PUSH Button IP(인터럽트 포함), Text_LCD IP, SEVEN_SEGMENT IP를 구현하여 AXI4를 통해 PS영역과 연결

PS영역에서 C언어로 디지털 시계기능을 구현



## **6.종합설계프로젝트1**

2019년도 1학기에 조정훈 교수님의 종합설계프로젝트1을 수행하면서 작성한 자율주행 코드

1학기 목표) 라즈베리파이와 라이다를 활용한 RC카를 IT-1호관 복도에서 직진주행 수행

### rplidar_ros 폴더
1. node.cpp 
  
라이다 회사에서 기본적으로 제공해주는 ros제어 코드로 이를 실행하면 라이다가 동작함
  
라이다로 받은 값을 이용할 수 있도록 msg를 이용해 client.cpp에 전달해줌


2. client.cpp
  
라이다 회사에서 기본적으로 제공해주는 ros용 코드를 수정해서 사용
  
라이다를 통해 받은 수많은 값들을 선별하여 어떤식으로 사용할껀지 코딩 진행
  
추가적으로 ros통신을 이용해 topic_subscriber.cpp에 msg를 전달하도록 코딩 (차량 제어를 위해)


### ros_tutorials_topic 폴더
1. topic_subscriber.cpp
  
라즈베리파이로 RC카의 서보모터,dc모터 제어를 통해 차량을 구동하는 차량 제어 코드
  
client.cpp에서 가공된 정보를 ROS통신으로 전달받고, 이 값을 PID제어로 직진주행 수행
  

참고) 2학기 목표는 jetson nano를 추가하여 nano에서 라이다를 제어하고 가공된 값을 라즈베리파이에 소켓통신으로 보내주어 라즈베리파이에서는 차량제어만 수행 + IT-2호관 2층 복도 전체(ㅁ자)를 자율주행 수행
