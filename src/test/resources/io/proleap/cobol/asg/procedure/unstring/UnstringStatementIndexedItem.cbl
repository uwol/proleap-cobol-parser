000010 IDENTIFICATION  DIVISION.                                        Y2612893
000050 PROGRAM-ID.     UnstringSample.                                  Y2612893
000200*=================================================================Y2612893
000210 ENVIRONMENT     DIVISION.                                        Y2612893
000220 CONFIGURATION SECTION.                                           Y2612893
000230 SPECIAL-NAMES.   DECIMAL-POINT IS COMMA.                         Y2612893
000240 INPUT-OUTPUT    SECTION.                                         Y2612893
000290*=================================================================Y2612893
000300 DATA            DIVISION.                                        Y2612893
000410*=================================================================Y2612893
000420 WORKING-STORAGE SECTION.                                         Y2612893
000430*=================================================================Y2612893
000440*    C-  Zähler                                                   Y2612893
000450 01  C-ZAEHLER                           PACKED-DECIMAL.          Y2612893
000460     05  C-UEB               PIC 9(06)     VALUE ZERO.            Y2612893
000470     05  C-D852A             PIC S9(9)     VALUE +0.              Y2612893
000480*=================================================================Y2612893
000930*    T-  Tabellen                                                 Y2612893
000940 01  T-TABELLEN.                                                  Y2612893
000950     05 T-WERT               PIC X(72)          OCCURS 11.        Y2612893
000960*=================================================================Y2612893
000970/                                                                 Y2612893
000980*=================================================================Y2612893
000990*    X-  Subskripte                                               Y2612893
001000 01  X-SUBSKRIPTE                        BINARY.                  Y2612893
001010     05  X-1                 PIC S9(4).                           Y2612893
001020     05  X-2                 PIC S9(4).                           Y2612893
001030     05  X-4                 PIC S9(4).                           Y2612893
001040*=================================================================Y2612893
001050/                                                                 Y2612893
001060*=================================================================Y2612893
001070*    Z-  Zwischenbereiche                                         Y2612893
001080 01  Z-ZWISCHENBEREICHE.                                          Y2612893
001090     05  Z-LAEN              PIC S9(5)     COMP-3.                Y2612893
001100     05  Z-DATUM.                                                 Y2612893
001110         10  Z-JAHR          PIC X(4).                            Y2612893
001120         10  Z-MONAT         PIC X(2).                            Y2612893
001130         10  Z-TAG           PIC X(2).                            Y2612893
001140         10  Z-ZEIT          PIC X(6).                            Y2612893
001150         10  FILLER          PIC X(5).                            Y2612893
001160     05  Z-WERT.                                                  Y2612893
001170         10  Z-WERT1         PIC X(72).                           Y2612893
001180         10  Z-WERT2         PIC X(72).                           Y2612893
001190         10  Z-WERT3         PIC X(72).                           Y2612893
001200         10  Z-WERT4         PIC X(72).                           Y2612893
001210     05  Z-BTRNR-E           PIC X(08).                           Y2612893
001220     05  Z-BTRNR-A           PIC X(08).                           Y2612893
001230     05  Z-EMP-1             PIC X(35).                           Y2612893
001240     05  Z-EMP-2             PIC X(35).                           Y2612893
001250     05  Z-EMP-3             PIC X(35).                           Y2612893
001260     05  Z-ABS-1             PIC X(35).                           Y2612893
001270     05  Z-ABS-2             PIC X(35).                           Y2612893
001280     05  Z-ABS-3             PIC X(35).                           Y2612893
001290     05  Z-BAND.                                                  Y2612893
001300         10  Z-BAND-NR       PIC 9(05).                           Y2612893
001310         10  FILLER          PIC X(15).                           Y2612893
001320     05  Z-UEB.                                                   Y2612893
001330         10  Z-UEB-1         PIC X(02)    VALUE 'EL'.             Y2612893
001340         10  Z-UEB-2         PIC 9(02)    VALUE 0.                Y2612893
001350*=================================================================Y2612893
       Procedure Division.                                                      
           perform B70100                                                       
           stop run.                                                            
005040*=================================================================Y2612893
005050 B70100.                                                          Y2612893
005070     MOVE 0                          TO X-2                       Y2612893
005080     UNSTRING Z-WERT                                              Y2612893
005090             DELIMITED BY ALL '#'                                 Y2612893
005100             INTO         T-WERT  (1)                             Y2612893
005110                          T-WERT  (2)                             Y2612893
005120                          T-WERT  (3)                             Y2612893
005130                          T-WERT  (4)                             Y2612893
005140                          T-WERT  (5)                             Y2612893
005150                          T-WERT  (6)                             Y2612893
005160                          T-WERT  (7)                             Y2612893
005170                          T-WERT  (8)                             Y2612893
005180                          T-WERT  (9)                             Y2612893
005190                          T-WERT  (10)                            Y2612893
005200                          T-WERT  (11)                            Y2612893
005210             TALLYING IN  X-2                                     Y2612893
005220     END-UNSTRING                                                 Y2612893
005230     PERFORM                                                      Y2612893
005240             VARYING X-1 FROM 1 BY 1                              Y2612893
005250             UNTIL   X-1 > X-2                                    Y2612893
005260         MOVE SPACES             TO Z-WERT                        Y2612893
005270*        UNSTRING Z-WERT                                          Y2612893
005270         UNSTRING T-WERT  (X-1)                                   Y2612893
005280                 DELIMITED BY ALL '='                             Y2612893
005290                           OR ALL '  '                            Y2612893
005300                 INTO         Z-WERT1                             Y2612893
005310                              Z-WERT2                             Y2612893
005320         END-UNSTRING                                             Y2612893
005330         EVALUATE TRUE                                            Y2612893
005340         WHEN Z-WERT1  = 'EMP-1'                                  Y2612893
005350             MOVE Z-WERT2            TO Z-EMP-1                   Y2612893
005360         WHEN Z-WERT1  = 'EMP-2'                                  Y2612893
005370             MOVE Z-WERT2            TO Z-EMP-2                   Y2612893
005380         WHEN Z-WERT1  = 'EMP-3'                                  Y2612893
005390             MOVE Z-WERT2            TO Z-EMP-3                   Y2612893
005400         WHEN Z-WERT1  = 'ABS-1'                                  Y2612893
005410             MOVE Z-WERT2            TO Z-ABS-1                   Y2612893
005420         WHEN Z-WERT1  = 'ABS-2'                                  Y2612893
005430             MOVE Z-WERT2            TO Z-ABS-2                   Y2612893
005440         WHEN Z-WERT1  = 'ABS-3'                                  Y2612893
005450             MOVE Z-WERT2            TO Z-ABS-3                   Y2612893
005460         WHEN Z-WERT1  = 'BTRNR-A'                                Y2612893
005470             MOVE Z-WERT2            TO Z-BTRNR-A                 Y2612893
005480         WHEN Z-WERT1  = 'BTRNR-E'                                Y2612893
005490             MOVE Z-WERT2            TO Z-BTRNR-E                 Y2612893
005500         WHEN OTHER                                               Y2612893
005510             CONTINUE                                             Y2612893
005520         END-EVALUATE                                             Y2612893
005530     END-PERFORM                                                  Y2612893
005540     .                                                            Y2612893
005550 B70100-EX.                                                       Y2612893
005560     EXIT.                                                        Y2612893
005570*=================================================================Y2612893
