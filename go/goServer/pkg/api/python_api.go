package api

import (
	"bufio"
	"fmt"
	"io"
	"os/exec"
)

func NewPythonApi() *PythonApi {
	//inputReader, inputWriter := io.Pipe()
	//outputReader, outputWriter := io.Pipe()

	command := exec.Command("tr", "a-z", "A-Z")
	input, _ := command.StdinPipe()
	output, _ := command.StdoutPipe()
	command.Start()
	return &PythonApi{cmd: command, out: &output, in: &input}
}

type PythonApi struct {
	cmd *exec.Cmd
	out *io.ReadCloser
	in  *io.WriteCloser
}

func (p *PythonApi) CheckStatus(word string) {

	fmt.Fprintf(*p.in, word+"\n")

	p.cmd.Wait()
	scan := bufio.NewScanner(*p.out)
	for scan.Scan() {
		fmt.Printf("in all caps: %q\n", scan.Text())
	}

}

func (p *PythonApi) Wait() {
	p.cmd.Wait()
}
