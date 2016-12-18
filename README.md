name-gen
========

Generate names using a markov chain.

To use, enter a .json file with name data as the first command line argument to the program. Every name
will be fed into the markov chain and a name will be generated using all the name data it was trained with. I will
soon have a .json file with many names with which to train the program.

This program works by creating a directed, weighted graph (the markov chain). Each node is a letter and each weight is
the probability of seeing the next letter. When generating a name, it starts at the start state and generating random
numbers, follows the graph until it hits the end state. The nodes that it passed through is the generated name.

