programa {
	funcao inicio() {
	    
	    real celsius
	    real fahrenheit
	    inteiro conversor
	    
	    escreva("Qual conversor deseja usar?\n\n")
	    
	    escreva("1 - Celsius para Fahrenheit\n")
	    escreva("2 - Fahrenheit para Celsius\n")
	    
	    leia(conversor)
	    
	    se(conversor == 1)
	    {
	        escreva("\n Informe a temperatura em celsius: \n")
	        leia(celsius)
	        
	        fahrenheit = (celsius * 1.8) + 32

          escreva(fahrenheit)
	        
	        //escreva(celsius + " °C é igual a " + fahrenheit + "°F")
	    }
	    senao se(conversor == 2)
	    {
	        escreva("\n Informe a temperatura em fahrenheit: \n")
	        leia(fahrenheit)
	        
	        celsius = (fahrenheit - 32 ) / 1.8
	        
	        //escreva(fahrenheit + " °F é igual a " + celsius + "°C\n")
	    }
	    
	    senao
	    {
	        escreva("\n\nInforme opção válida?\n\n")
	    
	        
	    }
		
	}
}
