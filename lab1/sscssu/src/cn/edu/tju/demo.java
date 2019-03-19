package cn.edu.tju;

public class demo {
   
	public int[] money = {50, 20, 5, 5, 1, 1, 1};  
    public demo() {

    }
    public boolean charge(int x) {  
        for (int i=0; i< money.length; i++) {  
            if (money[i]<x) {  
                x = x - money[i];  
            }
            else if (money[i]==x) { 
                x = x - money[i];
                return true; 
            }
            else {
                continue;
            }
        }
        return false;  
    }

    
}
