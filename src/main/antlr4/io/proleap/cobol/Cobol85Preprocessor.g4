/*
* Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
* All rights reserved.
*
* This software may be modified and distributed under the terms
* of the MIT license. See the LICENSE file for details.
*/

/*
* Cobol 85 Preprocessor Grammar for ANTLR4
*
* This is a preprocessor grammar for Cobol 85.
*/

grammar Cobol85Preprocessor;

startRule
   : (compilerOptions | copyStatement | execCicsStatement | execSqlStatement | execSqlImsStatement | replaceOffStatement | replaceArea | ejectStatement | skipStatement | titleStatement | charDataLine | NEWLINE)* EOF
   ;

// compiler options

compilerOptions
   : (PROCESS | CBL) compilerOption (COMMACHAR? compilerOption)*
   ;

compilerOption
   : ADATA | ADV | APOST 
   | (ARITH | AR) LPARENCHAR (E | EXTEND | C | COMPAT) RPARENCHAR 
   | AWO
   | BLOCK0 
   | (BUF | BUFSIZE) literal
   | C | CBLCARD 
   | CICS (LPARENCHAR literal COMMACHAR)? 
   | COBOL2 | COBOL3 
   | (CP | CODEPAGE) LPARENCHAR literal RPARENCHAR 
   | COMPILE | CPP | CPSM 
   | (CURR | CURRENCY) LPARENCHAR literal RPARENCHAR
   | D 
   | DATA LPARENCHAR literal RPARENCHAR
   | (DP | DATEPROC) (LPARENCHAR (FLAG | NOFLAG)? (TRIG | NOTRIG)? RPARENCHAR)?
   | DBCS | DECK | DEBUG | DIAGTRUNC | DLL | DTR | DU | DUMP | DYN | DYNAM
   | EDF | EPILOG | EXIT | EXP | EXPORTALL
   | FASTSRT | FEPI 
   | (F | FLAG) LPARENCHAR (E | I | S | U | W) (COMMACHAR (E | I | S | U | W))? RPARENCHAR
   | FLAGSTD LPARENCHAR (M | I | H) (D | N | S)? RPARENCHAR 
   | FSRT
   | GDS | GRAPHIC
   | INTDATE LPARENCHAR (ANSI | LILIAN) RPARENCHAR
   | LANGUAGE LPARENCHAR (CS | EN | JA | JP | KA | UE) RPARENCHAR 
   | LEASM | LENGTH | LIB | LIN 
   | (LC | LINECOUNT) LPARENCHAR literal RPARENCHAR 
   | LINKAGE | LIST
   | MAP 
   | MARGINS LPARENCHAR literal COMMACHAR literal (COMMACHAR literal)? RPARENCHAR 
   | MD 
   | MDECK (C | COMPILE | NOC | NOCOMPILE)?
   | NAME (ALIAS | NOALIAS)?
   | NATLANG LPARENCHAR (CS | EN | KA) RPARENCHAR
   | NOADATA | NOADV | NOAWO
   | NOBLOCK0
   | NOCBLCARD | NOCICS | NOCMPR2 
   | (NOC | NOCOMPILE) (LPARENCHAR (S | E | W) RPARENCHAR)? 
   | NOCPSM | NOCURR | NOCURRENCY
   | NOD | NODATEPROC | NODBCS | NODEBUG | NODECK | NODLL | NODP | NODTR | NODE | NODUMP | NODYN | NODYNAM
   | NOEDF | NOEPILOG | NOEXIT | NOEXP | NOEXPORTALL
   | NOF | NOFASTSRT | NOFEPI | NOFLAG | NOFLAGMIG | NOFLAGSTD | NOFSRT
   | NOGRAPHIC
   | NOLENGTH | NOLIB | NOLINKAGE | NOLIST
   | NOMAP | NOMD | NOMDECK
   | NONAME | NONUM | NONUMBER
   | NOOBJ | NOOBJECT | NOOFF | NOOFFSET | NOOPSEQUENCE | NOOPT | NOOPTIMIZE | NOOPTIONS
   | NOP | NOPROLOG
   | NORENT
   | NOS | NOSEQ | NOSEQUENCE | NOSOURCE | NOSPIE | NOSQL | NOSQLCCSID | NOSSR | NOSSRANGE | NOSTDTRUNC
   | NOTERM | NOTERMINAL | NOTEST | NOTHREAD
   | NOVBREF
   | NOWD | NOWORD
   | NSEQ | (NS | NSYMBOL) (NAT | NATIONAL | DBCS) 
   | NOVBREF
   | NOX | NOXREF
   | NOZWB
   | NUM | NUMBER 
   | NUMPROC LPARENCHAR (MIG | NOPFD | PFD) RPARENCHAR
   | OBJ | OBJECT | OFF | OFFSET 
   | OPMARGINS LPARENCHAR literal COMMACHAR literal (COMMACHAR literal)? RPARENCHAR 
   | OPSEQUENCE LPARENCHAR literal COMMACHAR literal RPARENCHAR 
   | (OPT | OPTIMIZE) (LPARENCHAR (FULL | STD) RPARENCHAR)? 
   | OP | OPTFILE | OPTIONS 
   | (OUT | OUTDD) LPARENCHAR cobolWord RPARENCHAR
   | (PGMN | PGMNAME) LPARENCHAR (CO | COMPAT | LM | LONGMIXED | LONGUPPER | LU | M | MIXED | U | UPPER) RPARENCHAR 
   | PROLOG
   | Q | QUOTE
   | RENT 
   | RMODE LPARENCHAR (ANY | AUTO | literal) RPARENCHAR
   | (SEQ | SEQUENCE) (LPARENCHAR literal COMMACHAR literal RPARENCHAR)? 
   | (SIZE | SZ) LPARENCHAR (MAX | literal) RPARENCHAR 
   | SOURCE | SP 
   | SPACE LPARENCHAR literal RPARENCHAR 
   | SPIE 
   | SQL (LPARENCHAR literal RPARENCHAR)? 
   | SQLCCSID | SSR | SSRANGE | SYSEIB
   | TERMINAL 
   | TEST (LPARENCHAR (HOOK | NOHOOK)? (SEP | SEPARATE | NOSEP | NOSEPARATE)? (EJPD | NOEJPD)? RPARENCHAR)? 
   | THREAD 
   | TRUNC LPARENCHAR (BIN | OPT | STD) RPARENCHAR
   | VBREF
   | WD | WORD
   | (XP | XMLPARSE) LPARENCHAR (X | XMLSS | C | COMPAT) RPARENCHAR 
   | XOPTS LPARENCHAR compilerOption (COMMACHAR? compilerOption)* RPARENCHAR 
   | (X | XREF) LPARENCHAR (FULL | SHORT)? RPARENCHAR
   | YEARWINDOW LPARENCHAR literal RPARENCHAR
   | ZWB
   ;

// exec cics statement

execCicsStatement
   : EXEC CICS charData END_EXEC DOT?
   ;

// exec sql statement

execSqlStatement
   : EXEC SQL charDataSql END_EXEC DOT?
   ;

// exec sql ims statement

execSqlImsStatement
   : EXEC SQLIMS charData END_EXEC DOT?
   ;

// copy statement

copyStatement
   : COPY copySource (NEWLINE* (directoryPhrase | familyPhrase | replacingPhrase | SUPPRESS))* NEWLINE* DOT
   ;

copySource
   : literal | cobolWord
   ;

replacingPhrase
   : REPLACING NEWLINE* replaceClause (NEWLINE+ replaceClause)*
   ;

// replace statement

replaceArea
   : replaceByStatement (copyStatement | charData)* replaceOffStatement?
   ;

replaceByStatement
   : REPLACE (NEWLINE* replaceClause)+ DOT
   ;

replaceOffStatement
   : REPLACE OFF DOT
   ;

replaceClause
   : replaceable NEWLINE* BY NEWLINE* replacement (NEWLINE* directoryPhrase)? (NEWLINE* familyPhrase)?
   ;

directoryPhrase
   : (OF | IN) NEWLINE* (literal | cobolWord)
   ;

familyPhrase
   : ON NEWLINE* (literal | cobolWord)
   ;

replaceable
   : literal | cobolWord | pseudoText | charDataLine
   ;

replacement
   : literal | cobolWord | pseudoText | charDataLine
   ;

// eject statement

ejectStatement
   : EJECT DOT?
   ;

// skip statement

skipStatement
   : (SKIP1 | SKIP2 | SKIP3) DOT?
   ;

// title statement

titleStatement
   : TITLE literal DOT?
   ;

// literal ----------------------------------

cobolWord
   : IDENTIFIER | charDataKeyword
   ;

literal
   : NONNUMERICLITERAL | NUMERICLITERAL
   ;

pseudoText
   : DOUBLEEQUALCHAR charData? DOUBLEEQUALCHAR
   ;

charData
   : (charDataLine | NEWLINE)+
   ;

charDataSql
   : (charDataLine | REPLACE | NEWLINE)+
   ;

charDataLine
   : (cobolWord | literal | TEXT | DOT)+
   ;

// keywords ----------------------------------

charDataKeyword
   : A | ADATA | ADV | ALIAS | ANSI | ANY | APOST | AR | ARITH | AUTO | AWO
   | B | BIN | BLOCK0 | BUF | BUFSIZE | BY
   | C | CBL | CBLCARD | CO | COBOL2 | COBOL3 | CODEPAGE | COMMACHAR | COMPAT | COMPILE | CP | CPP | CPSM | CS | CURR | CURRENCY
   | D | DATA | DATEPROC | DBCS | DEBUG | DECK | DIAGTRUNC | DLI | DLL | DP | DTR | DU | DUMP | DYN | DYNAM
   | E | EDF | EJECT | EJPD | EN | EPILOG | EXCI | EXIT | EXP | EXPORTALL | EXTEND
   | F | FASTSRT | FLAG | FLAGSTD | FULL | FSRT
   | G | GDS | GRAPHIC
   | H | HOOK
   | I | IN | INTDATE
   | J | JA | JP
   | K | KA 
   | L | LANGUAGE | LC | LENGTH | LIB | LILIAN | LIN | LINECOUNT | LINKAGE | LIST | LM | LONGMIXED | LONGUPPER | LPARENCHAR | LU
   | M | MAP | MARGINS | MAX | MD | MDECK | MIG | MIXED
   | N | NAME | NAT | NATIONAL | NATLANG
   | NO 
   | NOADATA | NOADV | NOALIAS | NOAWO
   | NOBLOCK0
   | NOC | NOCBLCARD | NOCICS | NOCMPR2 | NOCOMPILE | NOCPSM | NOCURR | NOCURRENCY
   | NOD | NODATEPROC | NODBCS | NODE | NODEBUG | NODECK | NODLL | NODUMP | NODP | NODTR | NODYN | NODYNAM
   | NOEDF | NOEJPD | NOEPILOG | NOEXIT | NOEXP | NOEXPORTALL
   | NOF | NOFASTSRT | NOFEPI | NOFLAG | NOFLAGMIG | NOFLAGSTD | NOFSRT
   | NOGRAPHIC
   | NOHOOK
   | NOLENGTH | NOLIB | NOLINKAGE | NOLIST
   | NOMAP | NOMD | NOMDECK
   | NONAME | NONUM | NONUMBER
   | NOOBJ | NOOBJECT | NOOFF | NOOFFSET | NOOPSEQUENCE | NOOPT | NOOPTIMIZE | NOOPTIONS
   | NOP | NOPFD | NOPROLOG
   | NORENT
   | NOS | NOSEP | NOSEPARATE | NOSEQ | NOSEQUENCE | NOSOURCE | NOSPIE | NOSQL | NOSQLCCSID | NOSSR | NOSSRANGE | NOSTDTRUNC
   | NOTERM | NOTERMINAL | NOTEST | NOTHREAD | NOTRIG
   | NOVBREF
   | NOWORD
   | NOX | NOXREF
   | NOZWB
   | NS | NSEQ | NSYMBOL
   | NUM | NUMBER | NUMPROC
   | O | OBJ | OBJECT | ON | OF | OFF | OFFSET | OPMARGINS | OPSEQUENCE | OPTIMIZE | OP | OPT | OPTFILE | OPTIONS | OUT | OUTDD
   | P | PFD | PGMN | PGMNAME | PPTDBG | PROCESS | PROLOG
   | Q | QUOTE
   | R | RENT | REPLACING | RMODE | RPARENCHAR
   | S | SEQ | SEQUENCE | SEP | SEPARATE | SHORT | SIZE | SOURCE | SP | SPACE | SPIE | SQL | SQLCCSID | SSR | SSRANGE | STD | SYSEIB | SZ
   | T | THREAD | TITLE | TERMINAL | TEST | TRIG | TRUNC
   | U | UE | UPPER
   | V | VBREF
   | W | WD
   | X | XMLPARSE | XMLSS | XOPTS | XREF
   | Y | YEARWINDOW
   | Z | ZWB
   ;

// lexer rules --------------------------------------------------------------------------------

// keywords
ADATA : A D A T A;
ADV : A D V;
ALIAS : A L I A S;
ANSI : A N S I;
ANY : A N Y;
APOST : A P O S T;
AR : A R;
ARITH : A R I T H;
AUTO : A U T O;
AWO : A W O;
BIN : B I N;
BLOCK0 : B L O C K '0';
BUF : B U F;
BUFSIZE : B U F S I Z E;
BY : B Y;
CBL : C B L;
CBLCARD : C B L C A R D;
CICS : C I C S;
CO : C O;
COBOL2 : C O B O L '2';
COBOL3 : C O B O L '3';
CODEPAGE : C O D E P A G E;
COMPAT : C O M P A T;
COMPILE : C O M P I L E;
COPY : C O P Y;
CP : C P;
CPP : C P P;
CPSM : C P S M;
CS : C S;
CURR : C U R R;
CURRENCY : C U R R E N C Y;
DATA : D A T A;
DATEPROC : D A T E P R O C;
DBCS : D B C S;
DEBUG : D E B U G;
DECK : D E C K;
DIAGTRUNC : D I A G T R U N C;
DLI : D L I;
DLL : D L L;
DP : D P;
DTR : D T R;
DU : D U;
DUMP : D U M P;
DYN : D Y N;
DYNAM : D Y N A M;
EDF : E D F;
EJECT : E J E C T;
EJPD : E J P D;
EN : E N;
END_EXEC : E N D '-' E X E C;
EPILOG : E P I L O G;
EXCI : E X C I;
EXEC : E X E C;
EXIT : E X I T;
EXP : E X P;
EXPORTALL : E X P O R T A L L;
EXTEND : E X T E N D;
FASTSRT : F A S T S R T;
FEPI : F E P I;
FLAG : F L A G;
FLAGSTD : F L A G S T D;
FSRT : F S R T;
FULL : F U L L;
GDS : G D S;
GRAPHIC : G R A P H I C;
HOOK : H O O K;
IN : I N;
INTDATE : I N T D A T E;
JA : J A;
JP : J P;
KA : K A;
LANGUAGE : L A N G U A G E;
LC : L C;
LEASM : L E A S M;
LENGTH : L E N G T H;
LIB : L I B;
LILIAN : L I L I A N;
LIN : L I N;
LINECOUNT : L I N E C O U N T;
LINKAGE : L I N K A G E;
LIST : L I S T;
LM : L M;
LONGMIXED : L O N G M I X E D;
LONGUPPER : L O N G U P P E R;
LPARENCHAR : '(';
LU : L U;
MAP : M A P;
MARGINS : M A R G I N S;
MAX : M A X;
MD : M D;
MDECK : M D E C K;
MIG : M I G;
MIXED : M I X E D;
NAME : N A M E;
NAT : N A T;
NATIONAL : N A T I O N A L;
NATLANG : N A T L A N G;
NO : N O;
NOADATA : N O A D A T A;
NOADV : N O A D V;
NOALIAS : N O A L I A S;
NOAWO : N O A W O;
NOBLOCK0 : N O B L O C K '0';
NOC : N O C;
NOCBLCARD : N O C B L C A R D;
NOCICS : N O C I C S;
NOCMPR2 : N O C M P R '2';
NOCOMPILE : N O C O M P I L E;
NOCPSM : N O C P S M;
NOCURR : N O C U R R;
NOCURRENCY : N O C U R R E N C Y;
NOD : N O D;
NODATEPROC : N O D A T E P R O C;
NODBCS : N O D B C S;
NODE : N O D E;
NODEBUG : N O D E B U G;
NODECK : N O D E C K;
NODLL : N O D L L;
NODUMP : N O D U M P;
NODP : N O D P;
NODTR : N O D T R;
NODYN : N O D Y N;
NODYNAM : N O D Y N A M;
NOEDF : N O E D F;
NOEJPD : N O E J P D;
NOEPILOG : N O E P I L O G;
NOEXIT : N O E X I T;
NOEXP : N O E X P;
NOEXPORTALL : N O E X P O R T A L L;
NOF : N O F;
NOFASTSRT : N O F A S T S R T;
NOFEPI : N O F E P I;
NOFLAG : N O F L A G;
NOFLAGMIG : N O F L A G M I G;
NOFLAGSTD : N O F L A G S T D;
NOFSRT : N O F S R T;
NOGRAPHIC : N O G R A P H I C;
NOHOOK : N O H O O K;
NOLENGTH : N O L E N G T H;
NOLIB : N O L I B;
NOLINKAGE : N O L I N K A G E;
NOLIST : N O L I S T;
NOMAP : N O M A P;
NOMD : N O M D;
NOMDECK : N O M D E C K;
NONAME : N O N A M E;
NONUM : N O N U M;
NONUMBER : N O N U M B E R;
NOOBJ : N O O B J;
NOOBJECT : N O O B J E C T;
NOOFF : N O O F F;
NOOFFSET : N O O F F S E T;
NOOPSEQUENCE : N O O P S E Q U E N C E;
NOOPT : N O O P T;
NOOPTIMIZE : N O O P T I M I Z E;
NOOPTIONS : N O O P T I O N S;
NOP : N O P;
NOPFD : N O P F D;
NOPROLOG : N O P R O L O G;
NORENT : N O R E N T;
NOS : N O S;
NOSEP : N O S E P;
NOSEPARATE : N O S E P A R A T E;
NOSEQ : N O S E Q;
NOSOURCE : N O S O U R C E;
NOSPIE : N O S P I E;
NOSQL : N O S Q L;
NOSQLCCSID : N O S Q L C C S I D;
NOSSR : N O S S R;
NOSSRANGE : N O S S R A N G E;
NOSTDTRUNC : N O S T D T R U N C;
NOSEQUENCE : N O S E Q U E N C E;
NOTERM : N O T E R M;
NOTERMINAL : N O T E R M I N A L;
NOTEST : N O T E S T;
NOTHREAD : N O T H R E A D;
NOTRIG : N O T R I G;
NOVBREF : N O V B R E F;
NOWD : N O W D;
NOWORD : N O W O R D;
NOX : N O X;
NOXREF : N O X R E F;
NOZWB : N O Z W B;
NS : N S;
NSEQ : N S E Q;
NSYMBOL : N S Y M B O L;
NUM : N U M;
NUMBER : N U M B E R;
NUMPROC : N U M P R O C;
OBJ : O B J;
OBJECT : O B J E C T;
OF : O F;
OFF : O F F;
OFFSET : O F F S E T;
ON : O N;
OP : O P;
OPMARGINS : O P M A R G I N S;
OPSEQUENCE : O P S E Q U E N C E;
OPT : O P T;
OPTFILE : O P T F I L E;
OPTIMIZE : O P T I M I Z E;
OPTIONS : O P T I O N S;
OUT : O U T;
OUTDD : O U T D D;
PFD : P F D;
PPTDBG : P P T D B G;
PGMN : P G M N;
PGMNAME : P G M N A M E;
PROCESS : P R O C E S S;
PROLOG : P R O L O G;
QUOTE : Q U O T E;
RENT : R E N T;
REPLACE : R E P L A C E;
REPLACING : R E P L A C I N G;
RMODE : R M O D E;
RPARENCHAR : ')';
SEP : S E P;
SEPARATE : S E P A R A T E;
SEQ : S E Q;
SEQUENCE : S E Q U E N C E;
SHORT : S H O R T;
SIZE : S I Z E;
SOURCE : S O U R C E;
SP : S P;
SPACE : S P A C E;
SPIE : S P I E;
SQL : S Q L;
SQLCCSID : S Q L C C S I D;
SQLIMS : S Q L I M S;
SKIP1 : S K I P '1';
SKIP2 : S K I P '2';
SKIP3 : S K I P '3';
SSR : S S R;
SSRANGE : S S R A N G E;
STD : S T D;
SUPPRESS : S U P P R E S S;
SYSEIB : S Y S E I B;
SZ : S Z;
TERMINAL : T E R M I N A L;
TEST : T E S T;
THREAD : T H R E A D;
TITLE : T I T L E;
TRIG : T R I G;
TRUNC : T R U N C;
UE : U E;
UPPER : U P P E R;
VBREF : V B R E F;
WD : W D;
WORD : W O R D;
XMLPARSE : X M L P A R S E;
XMLSS : X M L S S;
XOPTS: X O P T S;
XP : X P;
XREF : X R E F;
YEARWINDOW : Y E A R W I N D O W;
ZWB : Z W B;

// symbols
COMMENTTAG : '*>';
COMMACHAR : ',';
DOT : '.';
DOUBLEEQUALCHAR : '==';

// literals
NONNUMERICLITERAL : STRINGLITERAL | HEXNUMBER;
NUMERICLITERAL : [0-9]+;

fragment HEXNUMBER :
	X '"' [0-9A-F]+ '"'
	| X '\'' [0-9A-F]+ '\''
;

fragment STRINGLITERAL :
	'"' (~["\n\r] | '""' | '\'')* '"'
	| '\'' (~['\n\r] | '\'\'' | '"')* '\''
;

IDENTIFIER : [a-zA-Z0-9]+ ([-_]+ [a-zA-Z0-9]+)*;

// whitespace, line breaks, comments, ...
NEWLINE : '\r'? '\n';
COMMENTLINE : COMMENTTAG ~('\n' | '\r')* -> channel(HIDDEN);
WS : [ \t\f;]+ -> channel(HIDDEN);
TEXT : ~('\n' | '\r');

// case insensitive chars
A:('a'|'A');
B:('b'|'B');
C:('c'|'C');
D:('d'|'D');
E:('e'|'E');
F:('f'|'F');
G:('g'|'G');
H:('h'|'H');
I:('i'|'I');
J:('j'|'J');
K:('k'|'K');
L:('l'|'L');
M:('m'|'M');
N:('n'|'N');
O:('o'|'O');
P:('p'|'P');
Q:('q'|'Q');
R:('r'|'R');
S:('s'|'S');
T:('t'|'T');
U:('u'|'U');
V:('v'|'V');
W:('w'|'W');
X:('x'|'X');
Y:('y'|'Y');
Z:('z'|'Z');