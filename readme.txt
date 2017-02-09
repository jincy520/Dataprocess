## 0. Requirements
#### General
- Java 
## 1. Pre-processing
First, prepare train data.
java uploadProcessingTrain msr_train.utf8 trainData.txt trainTag.txt word.txt
This command has four inputs: gold file, output paths and dictionary path. 

Second, prepare test tag file as:
java uploadProcessingTestTag msr_test_gold.utf8 testTag.txt word.txt
This command has three inputs: gold file, output tag path and dictionary path. 

Third, prepare test data file as:
java uploadProcessingTestData msr_test_gold.utf8 testData.txt word.txt
This command has three inputs: gold result file, output data path and dictionary path. 


Question&Answering
If you face some problems on reading msr test data, replace the 3296 line in testData.txt to "577 166 31 84 1 184 271 1362 306 1524 610 247 45 228 137 429 445 165 378 260 247 27 238 1 238 238 238 28 4". 

Don't hesitate to contact me if you have problems. My email is "jingjingxu@pku.edu.cn".