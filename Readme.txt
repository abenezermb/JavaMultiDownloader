*MultiDownloader program
We have developed a simple program that tries to solve the time it takes to download multiple files one after another with the use of multithreading.
For the sake of simplicity we are gonna be using 5 threads that run at the same time to download multiple files from the Urls provided by the user.

* Class structure
Multidownloader  = Main Class where the program logics gets to be called
MDRunnable = Threading class that implements the Runnable interface and contains the program logic

* Instructions
1. Run `MultiDownloader.java` and proceed accordingly as instructed
2. I have used the Urls below to download the files
	* https://www.diva-portal.org/smash/get/diva2:1365314/FULLTEXT01.pdf
	* https://scet.berkeley.edu/wp-content/uploads/BlockchainPaper.pdf
	* https://core.ac.uk/download/pdf/161419816.pdf


