package Numbers;

class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) return false; 
        int reverse =0;
        int xCopy=x;
        int remaining=0;
        while(xCopy!=0){
        remaining=xCopy%10;
        reverse=reverse*10 + remaining;
        xCopy/=10;
        } 
        return (x==reverse)? true : false;
    }
}
