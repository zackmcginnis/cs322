# Instructions: Replace the text on the right of the "=" sign in the
# following line with your nine digit PSU ID number before you run "make".

PSUID = Please_enter_your_PSU_ID_in_the_Makefile_and_try_again

#------------------------------------------------------------------------

.PHONY	: all clean

CC = gcc -O -DPSU=$(PSUID)
SUBMIT = submit$(PSUID)
ZIPFILE = $(SUBMIT).zip

all	: program1 program2 program3 program4 program5 program6
	@echo "\nPreparing files for submission:\n"
	@./program1 "Total of numbers in two arrays" > out1
	@./program2 "Zeros in first plus nonzeros in second" > out2
	@./program3 "Reverse the first" > out3
	@./program4 "Swap arrays" > out4
	@./program5 "Pairwise compares and inner product" > out5
	@./program6 "Rotate" > out6
	@mkdir -p $(SUBMIT)
	@-cp program1.s out1 $(SUBMIT)
	@-cp program2.s out2 $(SUBMIT)
	@-cp program3.s out3 $(SUBMIT)
	@-cp program4.s out4 $(SUBMIT)
	@-cp program5.s out5 $(SUBMIT)
	@-cp program6.s out6 $(SUBMIT)
	@zip -mrq9 $(ZIPFILE) $(SUBMIT)
	@echo "Now you should upload $(ZIPFILE) to the dropbox on D2L.\n"

program1: program1.s munge.c
	@$(CC) -o program1 munge.c program1.s

program2: program2.s munge.c
	@$(CC) -o program2 munge.c program2.s

program3: program3.s munge.c
	@$(CC) -o program3 munge.c program3.s

program4: program4.s munge.c
	@$(CC) -o program4 munge.c program4.s

program5: program5.s munge.c
	@$(CC) -o program5 munge.c program5.s

program6: program6.s munge.c
	@$(CC) -o program6 munge.c program6.s

clean	:
	-rm program1 program2 program3 program4 program5 program6 $(ZIPFILE)
	-rm out1     out2     out3     out4     out5     out6     $(ZIPFILE)
	-rm -r $(SUBMIT)

