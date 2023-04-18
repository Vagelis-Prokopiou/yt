Run in release mode:

`dotnet run -c Release`

"Apache Bench" benchmarking command:

`ab -n 50000 -c 1020 http://127.0.0.1:8082/api/v1/users`

wrk benchmarking command:

`wrk -t 6 -c 100 -d 40s http://127.0.0.1:8082/api/v1/users`
