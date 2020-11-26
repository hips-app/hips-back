use master;
go
drop database if exists ingesoft2;
go
create database ingesoft2;
go
use ingesoft2;
go
ALTER DATABASE ingesoft2 set TRUSTWORTHY ON; 
GO 
EXEC dbo.sp_changedbowner @loginame = N'sa', @map = false 
GO 
sp_configure 'show advanced options', 1; 
GO 
RECONFIGURE; 
GO 
sp_configure 'clr enabled', 1; 
GO 
RECONFIGURE; 
GO

create table AccountType(
	id int primary key identity not null,
	name varchar(100) unique not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table Account(
	id int primary key identity not null,
	uid varchar(100) unique not null,
	type int references AccountType not null,
	email varchar(100) unique not null,
	firstName varchar(100) not null,
	lastName varchar(100) not null,
	profilePictureLink text,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table AccountTokenWhithelist(
	id int primary key identity not null,
	account int references Account not null,
	token text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table AccountSetting(
	id int primary key identity not null,
	account int references Account not null,
	notificationsEnabled bit not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table AccountDeviceToken(
	id int primary key identity not null,
	account int references Account not null,
	deviceId varchar(200) unique not null,
	token text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table AccountNotification(
	id int primary key identity not null,
	account int references Account not null,
	title varchar(100) not null,
	description text not null,
	opened bit default 0 not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table SpecialistType(
	id int primary key identity not null,
	name varchar(100) unique not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table SpecialistAccount(
	id int primary key identity not null,
	account int references Account not null,
	type int references SpecialistType not null,
	documentNumber varchar(100) unique not null,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table AdminAccount(
	id int primary key identity not null,
	account int references Account not null,
	isSuperAdmin bit default 0 not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table UserType(
	id int primary key identity not null,
	name varchar(100) unique not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table UserAccount(
	id int primary key identity not null,
	account int references Account not null,
	specialist int references SpecialistAccount null,
	type int references UserType not null,
	autoRenewSubscription bit default 0 not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table SubscriptionType(
	id int primary key identity not null,
	name varchar(100) unique not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table UserSubscription(
	id int primary key identity not null,
	type int references SubscriptionType not null,
	owner int references UserAccount not null,
	expirationDate datetime not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table UserMedicalData(
	id int primary key identity not null,
	owner int references UserAccount unique not null,
	birthDate date not null,
	heightInCentimeters int not null,
	weightInKilograms decimal not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table UserGoal(
	id int primary key identity not null,
	owner int references UserAccount not null,
	description text not null,
	expirationDate datetime not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table PhysicalExerciseType(
	id int primary key identity not null,
	name varchar(100) unique not null,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table PhysicalExercise(
	id int primary key identity not null,
	type int references PhysicalExerciseType not null,
	repetitionsAmount int,
	seriesAmount int,
	estimatedDurationInMinutes int,
	videoUrl text,
	imageUrl text,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go


create table SportPlan(
	id int primary key identity not null,
	goal int references UserGoal not null,
	startDate date not null,
	endDate date not null,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table WeekDay(
	id int primary key identity not null,
	name varchar(100) unique not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table DailySportPlan(
	id int primary key identity not null,
	sportPlan int references SportPlan not null,
	weekDay int references WeekDay not null,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table PlannedExercise(
	id int primary key identity not null,
	dailySportPlan int references DailySportPlan not null,
	physicalExercise int references PhysicalExercise not null,
	timeOfDay time,
	description text not null,
	checked bit default 0 not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table FoodPlan(
	id int primary key identity not null,
	goal int references UserGoal not null,
	startDate date not null,
	endDate date not null,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table DailyFoodPlan(
	id int primary key identity not null,
	foodPlan int references FoodPlan not null,
	weekDay int references WeekDay not null,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table PlannedMeal(
	id int primary key identity not null,
	dailyFoodPlan int references DailyFoodPlan not null,
	timeOfDay time,
	calories int,
	checked bit default 0 not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table FoodType(
	id int primary key identity not null,
	name varchar(100) unique not null,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table PlannedFood(
	id int primary key identity not null,
	plannedMeal int references PlannedMeal not null,
	foodType int references FoodType not null,
	amountInGrams int,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table ChatRoom(
	id int primary key identity not null,
	description text not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table ChatRoomMember(
	id int primary key identity not null,
	chatRoom int references ChatRoom not null,
	account int references Account not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go

create table ChatMessage(
	id int primary key identity not null,
	chatRoomMember int references ChatRoomMember not null,
	message text not null,
	sendedAt datetime not null,
	isActive bit default 1 not null,
	createdAt datetime not null,
	updatedAt datetime not null
);
go
