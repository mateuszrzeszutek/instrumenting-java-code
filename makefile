install-deps:
	@brew install pandoc basictex quarto mermaid-cli

build-slides:
	@pandoc -t beamer slides/presentation.md -o slides/presentation.pdf
