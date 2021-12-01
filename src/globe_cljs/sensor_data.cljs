(ns globe-cljs.sensor-data)


(def sensor-data '({:time        0,
                    :cell        [0 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [0 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "goes-west", :sensor "abi-meso-11"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [1 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [2 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [3 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [4 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}
                                   {:platform "goes-west", :sensor "abi-meso-4"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 6],
                    :coverage    #{{:platform "goes-east", :sensor "abi-meso-10"}
                                   {:platform "goes-west", :sensor "abi-3"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [5 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [6 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 7],
                    :coverage    #{{:platform "goes-east", :sensor "abi-meso-2"}
                                   {:platform "goes-west", :sensor "abi-3"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [7 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [8 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        0,
                    :cell        [9 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [0 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [1 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [2 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [3 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 6],
                    :coverage    #{{:platform "goes-east", :sensor "abi-meso-2"}
                                   {:platform "goes-west", :sensor "abi-3"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [4 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 7],
                    :coverage    #{{:platform "goes-east", :sensor "abi-meso-10"}
                                   {:platform "goes-west", :sensor "abi-3"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [5 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-west", :sensor "abi-meso-11"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [6 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "goes-east", :sensor "abi-1"}
                                   {:platform "goes-west", :sensor "abi-meso-4"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [7 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"
                                    {:platform "metop-yy", :sensor "avhhr-6"}
                                    {:platform "goes-east", :sensor "abi-1"}},}
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [8 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 0],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 1],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 2],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 3],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "metop-yy", :sensor "avhhr-6"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 4],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}}
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 5],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"}
                                   {:platform "noaa-xx", :sensor "viirs-5"}
                                   {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 6],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 7],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 8],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}
                   {:time        1,
                    :cell        [9 9],
                    :coverage    #{{:platform "goes-west", :sensor "abi-3"} {:platform "goes-east", :sensor "abi-1"}},
                    :computed_at "2021-08-02T15:16:05.558813"}))