package mini1;

public class doubles {
	public static void main(String[] args) {
String text="A. runtime = 10ms";
String accum="";
String list[]=text.split(" ");
boolean digitCheck=false;
boolean equalCheck=false;
int wordListLength=list.length;
for(int i=0; i<wordListLength; i++) {
	String word=list[i];
	if(Character.isAlphabetic(word.charAt(0))) {
		accum+=" "+word+" "+word;
		
}
	else {
		accum+=" "+word+" ";
	}
}
System.out.println(accum);
}
	
}