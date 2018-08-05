# Recruitment-Assessment-Spring-Boot
Restful web service to store invoice information

Special Instructions:

1. Please clone down the project, open in an IDE or via terminal.
  * git clone https://github.com/Thinah/Recruitment-Assessment-Spring-Boot.git
2. Build the project using maven command: 
  * mvn clean install
3. Execute the war file using the following command: 
  * mvn spring-boot:run

4. #h2 db:
  * console url:   https://localhost:8080/h2-console
  * Driver class:  org.h2.Driver
  * JDBC URL:      jdbc:h2:mem:testdb
  * Username:      sa
  * Password:      Blank (No password required)


#Input test JSON DATA for addInvoice @PostMapping -> (localhost:8080/invoice)

{
    "client": "Momentum",
    "vatRate": 15.00,
    "lineItems": [
    	{
    		"quantity": 3,
    		"description": "August main Invoicing",
    		"unitPrice":100
    	}, 
    	{
    		"quantity": 2,
    		"description": "August additional Invoicing",
    		"unitPrice": 100
    	}	
    ]
}

Thank you.
