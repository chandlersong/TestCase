// beginning of stocks.cpp file
#include <iostream>
#include "stock1.h" 

//constructors (verbose version)
Stock::Stock()
{
	std::cout << "Default constructor called" << std::endl;
	std::strcpy(company,"no name");
	shares = 0;
	share_val = 0.0;
	total_val = 0.0;
}

Stock::Stock(const char * co, int n, double pr)
{
	std::strncpy(company,co,29); //truncate co to fit company
	company[29] = '\0';
	
	if(n < 0 )
	{
		std::cerr << "Number of shares can't be negative;"
		          << company << " shares set to 0." << std::endl;
		shares = 0;
	}
	else{
		shares = n;
	}
	
	share_val = pr;
	set_tot();
}

//class destructor
Stock::~Stock()    // verbose class destructor
{
	std::cout << "Bye, " << company << "!" << std::endl;
}

void Stock::buy(int num, double price)
{
	if(num < 0)
	{
		std::cerr << " Number of shares purchased can’t be negative. "
		           << "Transaction is aborted" << std::endl;
				   
	}
	else
	{
		shares += num;
		share_val = price;
		set_tot();
	}
}

void Stock::sell(int num, double price)
{
	using std::cerr;
	if(num < 0 )
	{
		cerr << "Number of shares sold can't be negative. "
		     << "Transaction is aborted." << std::endl;
	}
}

void Stock::update(double price)
{
	share_val = price;
	set_tot();
}

void Stock::show()
{
	using std::cout;
	using std::endl;
	cout << "Company: " << company
	     << " Shares:" << shares << endl
		 << " Share Price: $" << share_val
		 << " Total Worth: $" << total_val << endl;
}