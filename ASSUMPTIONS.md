## List of assumptions made: ##
#### Tree: ####
1. List can only be traversed in one direction:
    1. If going downwards, tree traversal cannot go up to parent and down to another leaf.
    2. Example provided in requirements_thought_highlevel_logic text file.
2. A parent can have only two children.
3. Leaf has a limited number of parents:
    1. One parent   :- if on the far right/left of the tree
    2. Two parents  :- otherwise
    3. Exception made to first node (has not parents only children)
#### File: ####
1. The file input must be made up of integers only.
    1. Doubles, Strings or any other data type is not accepted.
2. There can only be one whitespace between each integer.
3. The file input must not have more than 2750 rows in total.
    1. Otherwise the memory heap for the JVM must be increased accordingly.
