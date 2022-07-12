
public class Operations {
	public Operations() {

	}

	public static String numberToWords(char n[]) {    
		int length = n.length;  
		if (length == 0) {      
			return "";
		}   
		if (length > 4) {   
			return "\n The given number has more than 4 digits.";  
		}     
		String[] firstDigit = new String[] {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};  
		String[] secondDigit = new String[] {"", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};   
		String[] multipleOfTens = new String[] {"",  "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};     
		String[] powerOfTens = new String[] {"Hundred", "Thousand"};  
		if (length == 1) {  
			return firstDigit[n[0]-'0'];  
		}  
		String stringSum = "";
		int x = 0;
		while (x < n.length) {  
			if (length >= 3) {  
				if (n[x] - '0' != 0) {  
					stringSum += firstDigit[n[x] - '0'] + " ";  
					stringSum += powerOfTens[length - 3] + " ";  
				}  
				--length;  
			} else {  
				if (n[x] - '0' == 1) {  
					int sum = n[x] - '0' + n[x + 1] - '0';  
					stringSum += secondDigit[sum];  
					break;  
				} else if (n[x] - '0' == 2 && n[x + 1] - '0' == 0) {   
					stringSum += "Twenty";  
					break;  
				} else {  
					int i = (n[x] - '0');  
					if (i > 0) {
						stringSum += multipleOfTens[i]+ " ";  
					} else {
						stringSum += "";  
					}
					++x;  
					if (n[x] - '0' != 0) {
						stringSum += firstDigit[n[x] - '0'];
					}	  
				}  
			}  
			++x;  
		}  
		return stringSum;
	}  
	/*	public static void main(String args[])  
	{  
		System.out.println(numberToWords("1111".toCharArray()));  
		System.out.println(numberToWords("673".toCharArray()));  
		System.out.println(numberToWords("85".toCharArray()));  
		System.out.println(numberToWords("5".toCharArray()));  
		System.out.println(numberToWords("0".toCharArray()));  
		System.out.println(numberToWords("20".toCharArray()));  
		System.out.println(numberToWords("1000".toCharArray()));  
		System.out.println(numberToWords("12345".toCharArray()));  
		
		System.out.println(numberToWords("".toCharArray()));  
	}*/
}
