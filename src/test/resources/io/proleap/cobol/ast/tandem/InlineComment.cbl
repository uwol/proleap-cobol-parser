 IDENTIFICATION DIVISION.
 PROGRAM-ID. INLINE-COMMENT.
 DATA DIVISION.
 WORKING-STORAGE SECTION.
 01  Student.
   03  nachname    pic x(20).
   03  vorname     pic x(20).
   03  geschlecht  pic x(1).  *> ANSI End-of-Line Comment 
     88  mann      values 'M' 'm'. 
	 88  frau      value 'F', 'f', 'W', 'w'.
	 88  egal      values 'a' thru 'z',
	      'A' thru 'Z'.