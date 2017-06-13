package com.example.ottr006.ngocvy;


public class Utilities {
	
	/**
	 * Hàm chuyển đổi thời gian từ milisecond thành đúng định dạng h:m:s
	 * */
	public String milliSecondsToTimer(long milliseconds){
		String finalTimerString = "";
		String secondsString = "";
		
		// Chuyển đổi tổng thời gian thành đúng định đạng
		   int hours = (int)( milliseconds / (1000*60*60));
		   int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
		   int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
		   // Chèn giờ nếu có
		   if(hours > 0){
			   finalTimerString = hours + ":";
		   }
		   
		   // Nếu có 1 chữ số
		   if(seconds < 10){ 
			   secondsString = "0" + seconds;
		   }else{
			   secondsString = "" + seconds;}
		   
		   finalTimerString = finalTimerString + minutes + ":" + secondsString;
		
		// Trả về thời gian
		return finalTimerString;
	}
	
	/**
	 * Function to get Progress percentage
	 * @param currentDuration
	 * @param totalDuration
	 * */
	public int getProgressPercentage(long currentDuration, long totalDuration){
		Double percentage = (double) 0;
		
		long currentSeconds = (int) (currentDuration / 1000);
		long totalSeconds = (int) (totalDuration / 1000);
		
		// calculating percentage
		percentage =(((double)currentSeconds)/totalSeconds)*100;
		
		// return percentage
		return percentage.intValue();
	}

	/**
	 * Function to change progress to timer
	 * @param progress - 
	 * @param totalDuration
	 * returns current duration in milliseconds
	 * */
	public int progressToTimer(int progress, int totalDuration) {
		int currentDuration = 0;
		totalDuration = (int) (totalDuration / 1000);
		currentDuration = (int) ((((double)progress) / 100) * totalDuration);
		
		// return current duration in milliseconds
		return currentDuration * 1000;
	}
}
