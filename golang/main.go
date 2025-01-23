package main

import (
	"fmt"
	"net/http"
)

func main() {
	const numStr string = "321"

	http.HandleFunc("/hello", func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Hello, World! "+numStr)
	})

	fmt.Println("Server starting on port 6060...")
	fmt.Println(numStr)
	err := http.ListenAndServe(":8080", nil)
	if err != nil {
		fmt.Println("Error starting server:", err)
	}
}
