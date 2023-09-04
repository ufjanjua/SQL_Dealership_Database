Create Database DealershipNetwork;

use DealershipNetwork;

create table Customer
(
	Cid int primary key,
    CName varchar(45),
    CAddress varchar (100),
    CEmail varchar (100),
    CPhoneNum varchar (10)
);

create table Dealership
(
	Did int primary key,
    DName varchar (45),
    DAddress varchar (100)
);

Create table Stock
(
	VIN int primary key,
    Make varchar (45),
    Model varchar (45),
    vehicle_year int,
    Color varchar (45),
    Price real,
    Did int,
    foreign key (Did) references Dealership (Did)
);

Create table Purchase
(
	Cid int,
    Did int,
    VIN int,
    foreign key (Cid) references Customer (Cid),
	foreign key (Did) references Dealership (Did),
    foreign key (VIN) references Stock (VIN)
);

