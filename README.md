# Performance test with [Artemis-odb](https://github.com/junkdog/artemis-odb) entity system

Simply create 600 entities per second, and toggle the entity life of 10 seconds by press Q key.

Result shows the performance drops gradually after 120,000 entities are created. The removal of entities also acts very responsively.

Performance is significantly better than [LibGdx Ashley](https://github.com/libgdx/ashley). Refering [test](https://github.com/yichen0831/PerformanceAshley).
