package main

import (
	"bytes"
	"fmt"
	"io"
	"os"
	"os/exec"
	"time"
)

func main() {
	cmd := exec.Command("tr", "a-z", "A-Z")
	input, _ := cmd.StdinPipe()
	r, w, _ := os.Pipe()
	cmd.Stdout = w

	go func() {
		for i := 1; i <= 10; i++ {
			time.Sleep(time.Duration(2) * time.Second)
			fmt.Printf("run command \n")
			fmt.Fprintf(input, "bbbb \n")
		}
	}()
	outC := make(chan string)
	go func() {
		for {
			var buf bytes.Buffer
			io.Copy(&buf, r)
			outC <- buf.String()

		}
	}()

	go func() {
		for {
			out := <-outC

			// reading our temp stdout
			fmt.Printf("previous output: %q", out)
		}
	}()
	cmd.Start()
	fmt.Printf("cmd command \n")
	cmd.Wait()
}
