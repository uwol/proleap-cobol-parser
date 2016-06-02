       identification division.
       program-id. testcase.
       environment division.
       data division.
       working-storage section.
       01 data-item-x4abbr pic x(4).
       01 data-item-x4 picture x(4).
       01 data-item-x4d pic x(4) usage display.
       01 data-item-94abbr pic 9(4).
       01 data-item-94 picture 9(4).
       01 data-item-94d pic 9(4) display.
       01 data-item-94c pic 9(4) comp.
       01 data-item-942 pic 9(4)v99.
       01 data-item-942d pic 9(4)v99 display.
       01 data-item-942c pic 9(4)v99 comp.
       01 data-group.
         03 data-group-item1 pic x(4).
         03 data-group-item2 pic x(4).
       01 data-item-ne1 pic zzz9 blank zero.
       01 data-item-ne2 pic zzz9.99 blank zero.
       procedure division.
          stop run.