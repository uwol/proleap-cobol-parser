 IDENTIFICATION DIVISION.
 PROGRAM-ID. COMMASPACE.
 DATA DIVISION.
 WORKING-STORAGE SECTION.
 01  Student.
   03  nachname    pic x(20).
   03  vorname     pic x(20).
   03  geschlecht  pic x(1).
     88  mann      values 'M' 'm'. 
	 88  frau      value 'F', 'f', 'W', 'w'.
	 88  egal      values 'a' thru 'z',
	      'A' thru 'Z'
* SPACE after COMMA required?		  
* OK	  '1', '2', '3'.
		  '1', '2','3'.