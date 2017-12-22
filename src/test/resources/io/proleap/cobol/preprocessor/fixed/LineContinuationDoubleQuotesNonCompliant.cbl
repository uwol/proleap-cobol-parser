000000 IDENTIFICATION  DIVISION.                                        12345678
000000 PROGRAM-ID.     LNECNTDBLQTNONCOMPLIANT.                         12345678
000000 DATA            DIVISION.                                        12345678
000000 WORKING-STORAGE SECTION.                                         12345678
000000        01 FILLER PIC X(99)          VALUE '123456789012345       12345678
000000-          '-----------------------------------------------------'12345678
000000-                                                                .12345678
000000*By specification, a line continuation following a quote in       12345678
000000*column 72 has to start with two quotes. So this example is       12345678
000000*non-compliant, but might happen in real-life.                    12345678