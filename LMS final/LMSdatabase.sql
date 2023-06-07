  Create database OOPLMS;
  
  use OOPLMS;
  
  CREATE TABLE User
  (Id varchar(11) PRIMARY KEY,
   First_Name varchar(100),
   Last_Name varchar(100),
   Gender char(1),
   Photo LONGBLOB,
   E_mail  varchar(50),
   Password varchar(20),
   DOB date,
   Department_id varchar(5)
   );
  
    CREATE TABLE Department
    (Department_ID varchar(5) PRIMARY KEY,
     Department_Name varchar(50));
    
    CREATE TABLE User_Phone
    (User_Id varchar(11)PRIMARY KEY,
    Phone_No char(10));
  
	CREATE TABLE Admin
    (User_Id varchar(11)PRIMARY KEY);
       
  	CREATE TABLE Technical_Officer
    (User_Id varchar(11)PRIMARY KEY);
  
   	CREATE TABLE Lecturer
    (User_Id varchar(11)PRIMARY KEY,
    Position varchar(20),
    Course varchar(20));
  
  	CREATE TABLE Student
    (User_Id varchar(11)PRIMARY KEY,
	 Acedamic_year varchar(7));
     
  
  CREATE TABLE Notice
    (Notice_ID INT PRIMARY KEY AUTO_INCREMENT,
     Submit_Date date,
     Title varchar(50), 
     Notice LONGBLOB,
     Admin_ID varchar(11),
	 Department_ID varchar(5));
  
  CREATE TABLE mark
  (Mark_ID  int(11) PRIMARY KEY AUTO_INCREMENT,
   Eligibility varchar(3),
   SGPA float(5,4),
   CGPA float(5,4),
   Grade char(2),
   Quiz1 int(3),
   Quiz2 int(3),
   Quiz3 int(3),
   Quiz4 int(3),
   Assessment1 int(3),
   Assessment2 int(3),
   Mid_Theory int(3),
   Mid_Practical int(3),
   End_Theory int(3),
   End_Practical int(3),
   Final_Marks int(3),
   Lecturer_ID varchar(11),
   Student_ID varchar(11),
   Course_ID varchar(7)
);
  
  CREATE TABLE course (
   Course_ID varchar(7)PRIMARY KEY,
   Course_Name  varchar(100),
  Course_Type varchar(20),
  No_Of_Credit int(1),
  Total_Lecture_Hours int(1),
  GPA_Status varchar(20),
  Department_ID varchar(5)
);
  
   CREATE TABLE course_registration (
  Registered_ID INT PRIMARY KEY AUTO_INCREMENT,
  Level varchar(1),
  Semester varchar(1),
  course_code1 varchar(30),
  course_code2 varchar(30),
  course_code3 varchar(30),
  course_code4 varchar(30),
  Student_ID varchar(11)
);


  CREATE TABLE registered_course (
  Registered_ID varchar(7),
  Course_ID varchar(7)
);
  

CREATE TABLE Lecture_Mateial
(Material_ID INT PRIMARY KEY AUTO_INCREMENT,
Description varchar(50),
Material LONGBLOB,
Course_ID varchar(7),
Lecturer_ID varchar(11));


 CREATE TABLE Attendance
   (Attendance_ID INT PRIMARY KEY AUTO_INCREMENT,
	 Date  Date,
	 Lecture_type varchar(20),
	 Time time,
     Course_ID varchar(7),
     Student_id varchar(11));
  
 CREATE TABLE Time_Table
   (TimeTable_Id  INT PRIMARY KEY AUTO_INCREMENT,
    Time_Table LONGBLOB,
	Department_ID varchar(5),
    Admin_ID varchar(11));



  CREATE TABLE medical 
  (Medical_id INT PRIMARY KEY AUTO_INCREMENT, 
   Absent_Date date NOT NULL,
   Committe_Desicion varchar(20),
   Medical_document LONGBLOB,
   Student_id varchar(11)
   );

CREATE TABLE request_course
(Course_id varchar(7) PRIMARY KEY,
Medical_id INT);




insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Admin_001','Jagath','Jayasinghe','M','abc','jagathjayasinghe@gmail.com','Jagath123','1985-06-03','ICT');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Lec_001','Kamal','Perera','M','def','kamalperera@gmail.com','Kamal123','1988-10-25','ET');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Lec_002','Nishani','Fernando','F','aaddef','nishanifernandoa@gmail.com','Nishanil123','1987-07-09','BST');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Lec_003','Apeksha','Kodithuwakku','F','ghi','apekshakodithuwakku@gmail.com','Apeksha123','1995-12-01','ICT');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Lec_004','Chathuranga','Adhikari','M','jkl','chathurangaadhikari@gmail.com','Chathuranga123','1990-04-28','ET');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Tec_001','Rasanga','Malshan','M','mno','rasangermalshan@gmail.com','Rasangal123','1992-08-06','ET');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Tec_002','Deepana','Vishwajith','M','pqrs','deepanavishwajith@gmail.com','Deepana123','1994-02-20','ICT');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_001','Pathum','Perera','M','tuv','pathumperera@gmail.com','Pathum123','1999-11-01','ICT');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_002','Imalka','Munasinghe','F','wxy','imalkamunasinghe@gmail.com','Imalka123','2000-03-06','ICT');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_003','Rusiru','Wijeyakoon','M','zab','rusiruwijeyakoon@gmail.com','Rusiru123','2000-09-30','ICT');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_004','Harith','Kalutharage','M','cde','harithkalutharage@gmail.com','Harith123','2000-08-14','ET');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_005','Nadith','Hewapathirana','M','fgh','nadithhewapthirana@gmail.com','naditha123','2000-07-06','ET');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_006','Budhima','Karunarathna','M','ijk','budhimakarunarathna@gmail.com','Budhima123','2000-12-07','ET');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_007','Achini','Weerakkodi','F','lmn','achiniweerakkodi@gmail.com','Achini123','2000-06-17','BST');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_008','Sadewni','Samaraweera','F','opq','sadewnisamarweera@gmail.com','Sadewni123','2000-03-06','BST');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_009','Hansani','Thewarapperuma','F','rst','hansanithewarapperuma@gmail.com','Hasini123','2000-11-21','BST');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_010','Piumali','Madubashini','F','uvw','pumalimadubashini@gmail.com','Piumali123','2000-05-10','BST');

insert into User (id,First_Name,Last_Name,Gender,Photo,E_mail,Password,DOB,Department_id)
values('Stu_011','Ahinsa','Sewwandi','F','xyz','ahinsasewwandi@gmail.com','Ahinsa123','2000-02-16','BST');

insert into Department (Department_Id,Department_Name)
values('ICT','Information and Communication Technology');

insert into Department (Department_Id,Department_Name)
values('ET','Engineering Technology');

insert into Department (Department_Id,Department_Name)
values('BST','Bio System Technology');


insert into User_Phone(User_Id,Phone_No)
values('Admin_001','0751235556');

insert into User_Phone(User_Id,Phone_No)
values('Lec_001','0711247556');

insert into User_Phone(User_Id,Phone_No)
values('Lec_002','0771247588');

insert into User_Phone(User_Id,Phone_No)
values('Lec_003','0781247236');

insert into User_Phone(User_Id,Phone_No)
values('Lec_004','0721347486');

insert into User_Phone(User_Id,Phone_No)
values('Tec_001','076667456');

insert into User_Phone(User_Id,Phone_No)
values('Tec_002','0781247985');

insert into User_Phone(User_Id,Phone_No)
values('Stu_001','0756847204');

insert into User_Phone(User_Id,Phone_No)
values('Stu_002','0724624622');

insert into User_Phone(User_Id,Phone_No)
values('Stu_003','0714412737');

insert into User_Phone(User_Id,Phone_No)
values('Stu_004','0722973714');

insert into User_Phone(User_Id,Phone_No)
values('Stu_005','0713634421');

insert into User_Phone(User_Id,Phone_No)
values('Stu_006','0775634128');

insert into User_Phone(User_Id,Phone_No)
values('Stu_007','0754621389');

insert into User_Phone(User_Id,Phone_No)
values('Stu_008','0783692584');

insert into User_Phone(User_Id,Phone_No)
values('Stu_009','0711247471');

insert into User_Phone(User_Id,Phone_No)
values('Stu_010','0776252486');

insert into Admin(User_Id)
values('Admin_001');


insert into Technical_Officer(User_Id)
values('Tec_001');

insert into Technical_Officer(User_Id)
values('Tec_002');

insert into Lecturer(User_Id ,Position,Course)
values('Lec_001','Temporary','ET');

insert into Lecturer(User_Id ,Position,Course)
values('Lec_002','Temporary','BST');

insert into Lecturer(User_Id ,Position,Course)
values('Lec_003','Permanent','ICT');

insert into Lecturer(User_Id ,Position,Course)
values('Lec_004','Permanent','ET');
 
 
insert into Student  (User_Id , Acedamic_year)
values( 'Stu_001','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_002','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_003','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_004','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_005','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_006','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_007','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_008','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_009','2020');	

insert into Student  (User_Id , Acedamic_year)
values( 'Stu_010','2020');	


insert into Notice(Submit_Date,Title,Notice,Admin_ID,Department_ID)
values('2022-01-25','Regarding extra lectures',' ','Admin_001','BST');

insert into Notice(Submit_Date,Title,Notice,Admin_ID,Department_ID)
values('2022-05-18','Regarding a postpone lectures',' ','Admin_001','ICT');

insert into Notice(Submit_Date,Title,Notice,Admin_ID,Department_ID)
values('2022-01-25','Regarding about assignment handover place',' ','Admin_001','ET');


insert into mark(Eligibility,SGPA,CGPA,Grade,Quiz1,Quiz2,Quiz3,Quiz4,Assessment1,Assessment2,Mid_Theory,Mid_Practical,
End_Theory,End_Practical,Final_Marks,Lecturer_ID,Student_ID,Course_ID)
values("Eli",3.5,3.6,"A",15,15,15,15,30,30,35,25,67,35,78,"Lec001","stu001","Tp1212");  
insert into mark(Eligibility,SGPA,CGPA,Grade,Quiz1,Quiz2,Quiz3,Quiz4,Assessment1,Assessment2,Mid_Theory,Mid_Practical,
End_Theory,End_Practical,Final_Marks,Lecturer_ID,Student_ID,Course_ID)
values("Eli",3.4,3.7,"b",12,15,13,15,20,30,25,25,47,35,68,"Lec001","stu002","Tp1212");
  





  

     
    
     
