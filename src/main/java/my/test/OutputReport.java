package my.test;

public class OutputReport {
	public String ErrorMessage="";
	public String normalizedinput="";
	public int display_inside_if=0;
	public int display_outside_if=0;
	public String filename="";
	
	public static final String getHead(){
		return "filename;display_inside_if;display_outside_if;error_message\n";
	}
	
	public String toString(){
		return filename+";"+this.display_inside_if+";"+this.display_outside_if+";"+this.ErrorMessage+"\n";
	}
	
}
