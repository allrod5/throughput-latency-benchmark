package main

import (
	"github.com/gin-gonic/gin"
	"io/ioutil"
	"net/http"
	"strings"
)

func main() {
	app := gin.Default()

	// Method:   GET
	// Resource: http://localhost:8080/
	app.GET("/", func(ctx *gin.Context) {
		responses := make([]string, 100)
		ch := make(chan string)

		for i := 0; i < 100; i++ {
			go func(url string, i int, c chan string) {
				resp, _ := http.Get(url)
				body, _ := ioutil.ReadAll(resp.Body)
				responses[i] = string(body)
				c <- responses[i]
			}("http://localhost:2000", i, ch)
		}
		for i := 0; i < 100; i++ {
			<-ch
		}
		ctx.String(http.StatusOK, strings.Join(responses, ","))
	})

	// http://localhost:8080
	// http://localhost:8080/ping
	// http://localhost:8080/hello
	app.Run(":1995")
}
