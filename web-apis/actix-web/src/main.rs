#[actix_web::main]
async fn main() -> std::io::Result<()> {
    // Running at 127.0.0.1:8080
    let address = "0.0.0.0:8083";
    println!("Running at {address}");
    let listener = std::net::TcpListener::bind(address).expect("Failed to bind to port 8083");
    api_actix_web::run(listener)?.await
}
