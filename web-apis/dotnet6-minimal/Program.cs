using System.Collections.Generic;
using Microsoft.AspNetCore.Builder;
using dotnet_6_minimal_wep_api.Models;

var builder = WebApplication.CreateBuilder(args);
var app = builder.Build();

// Route
app.MapGet("/api/v1/users", () => {
    var list = new List<User>(1000);
    for (int index = 1; index < 1001; index++) {
        list.Add(new User {
                Id = index,
                Age = 25,
                First_Name = "First_Name" + index,
                Last_Name = "Last_Name" + index,
                Framework = "dotnet6 minimala"
            }
        );
    }

    return list;
});

// Run
app.Run();