# CSC1031 - Module Repository

Welcome to the **CSC1031** repository! This repository contains coursework and assignments for the CSC1031 module.

**Note**: This directory structure representation was generated with the assistance of AI because the author was feeling a bit lazy.

## Repository Structure

The files in this repository are organized by weeks to reflect the progression of the course. Each week’s assignment and related files are stored in their respective folders. The files are `.java` files, and some of them include runner classes for execution.

```
.
├── W1/ 
│ ├── Assignment1.java 
│ ├── Assignment1Runner.java 
├── W2/ 
│ ├── Assignment2.java 
│ ├── Assignment2Runner.java 
├── W3/ 
│ ├── Assignment3.java 
│ ├── Assignment3Runner.java 
├── W4/ 
│ ├── Assignment4.java 
│ ├── Assignment4Runner.java 
├── W5/ 
│ ├── Assignment5.java 
│ ├── Assignment5Runner.java 
└── W6/ 
    ├── Assignment6.java 
    ├── Assignment6Runner.java
```

## Compilation and Execution

All compiled `.class` files are ignored by the `.gitignore` to keep the repository clean. To compile the Java files, you can use the following command:

```bash
javac W1/Assignment1.java
```

Replace W1/Assignment1.java with the path to the Java file you wish to compile.

### Running the Program

After compiling, you can run the program using:

```bash
java W1.Assignment1Runner
```

Make sure to replace `Assignment1Runner` with the appropriate runner class for your specific assignment.

### Missing Week

W7 is missing due to this being a reading week.

### Additional Notes

Due to this module being entirely based around java, there was of course a lot of little things that had to be changed for the testing service to properly accept the files.
With the given file structure, VSCode would always cite an error if I didn't include the package name at the top of the file. But this would of course break once uploaded, so much of it is commented out.

To mention one other difficulty, in Week 6 the different versions of GameLobby all required the class to be internally referred to as GameLobby, despite the file name changing across versions. I didn't even realise until GameLobby3 that I was supposed to have been calling getPlayerName() getName() instead.

Basically, these files are weird and I wouldn't recommend running them by themselves without expecting to have to fix a handful of errors.
