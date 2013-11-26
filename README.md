# cgol

Obligatory implementation of Conway's Game of Life. Deliberately non-clever, relatively straightforward implementation; because it's there.

## Usage

```clojure
(use 'cgol.core)
(use 'clojure.pprint)
(pprint (take 5 (gol blinker-board)))
(pprint (take 5 (gol glider-board)))
```

## License

Copyright © 2013 David Rupp

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
