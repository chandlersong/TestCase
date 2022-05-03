package api

import (
	"fmt"
	"io"
	"io/ioutil"
	"os/exec"
)

func NewPythonApi() *PythonApi {
	inputReader, inputWriter := io.Pipe()
	outputReader, outputWriter := io.Pipe()

	command := exec.Command("tr", "a-z", "A-Z")
	command.Stdin = inputReader
	command.Stdout = outputWriter
	command.Start()
	return &PythonApi{cmd: command, in: inputWriter, out: outputReader}
}

type PythonApi struct {
	cmd *exec.Cmd
	out *io.PipeReader
	in  *io.PipeWriter
}

func (p *PythonApi) CheckStatus(word string) {

	fmt.Fprintf(p.in, "%v\n", word)
	out, _ := ioutil.ReadAll(p.out)
	fmt.Printf("in all caps: %q\n", out)
}

func (p *PythonApi) Wait() {
	p.cmd.Wait()
}
