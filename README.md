name-gen
========

Generate names using a markov chain.

This program works by creating a directed, weighted graph (the markov chain). Each node is a letter and each weight is
the probability of seeing the next letter. When generating a name, it starts at the start state and generating random
numbers, follows the graph until it hits the end state. The nodes that it passed through is the generated name.

To use, enter a .json file with name data as the first command line argument to the program. Every name
will be fed into the markov chain and a name will be generated using all the name data it was trained with.

To train the markov chain the `names.json` file can be used. This is a json array of 19,439 baby names from 2015 that I compiled from
[Baby Names from Social Security Card Applications-National Level Data](https://catalog.data.gov/dataset/baby-names-from-social-security-card-applications-national-level-data).

Running name-gen ten times produces the following names:

- ammar
- shameres
- rierimylin
- ilylichadete
- gianahicerawarmmonan
- taserell
- rytiacy
- ti
- nitamindeade
- ynn

So yeah...it kind of sucks.
