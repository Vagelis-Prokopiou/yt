#![allow(non_snake_case)]

use std::net::TcpListener;

use actix_web::dev::Server;
use actix_web::{web, App, HttpResponse, HttpServer};

use crate::models::User;

mod models;

pub fn get_users() -> Vec<User> {
    let mut users = Vec::with_capacity(1000);
    for index in 1..1001_u16 {
        users.push(User {
            Id: index,
            Age: 25,
            First_Name: format!("First_Name{}", index),
            Last_Name: format!("Last_Name{}", index),
            Framework: "Rust (actix-web)".to_owned(),
        })
    }
    users
}

pub fn get_one_user() -> User {
    User {
        Id: 1,
        Age: 25,
        First_Name: "Michael".to_string(),
        Last_Name: "Jordan".to_string(),
        Framework: "actix-web".to_string(),
    }
}

async fn users() -> HttpResponse {
    let users = get_users();
    // let users = get_one_user();
    // let users = web::block(|| get_users()).await.unwrap();
    HttpResponse::Ok().json(users)
}

pub fn run(listener: TcpListener) -> Result<Server, std::io::Error> {
    let server = HttpServer::new(move || App::new().route("users", web::get().to(users)))
        // Setting the correct workers made a difference.
        .workers(num_cpus::get())
        // .workers(num_cpus::get_physical())
        .listen(listener)?
        .run();
    Ok(server)
}
