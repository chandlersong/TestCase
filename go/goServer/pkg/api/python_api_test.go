package api

import (
	"testing"
)

func TestHello(t *testing.T) {

	t.Run("test call python api", func(t *testing.T) {
		api := NewPythonApi()
		go func() {
			api.CheckStatus("two")
			api.CheckStatus("one")
		}()
		api.Wait()
	})

	t.Run("run reactive api", func(t *testing.T) {

	})

}
