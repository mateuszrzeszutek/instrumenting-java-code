.DEFAULT_GOAL := build-slides

install-deps:
	@brew install pandoc basictex quarto mermaid-cli
	@quarto install --no-prompt extension pandoc-ext/diagram

build-slides:
	@pandoc -t beamer -f markdown+implicit_figures presentation/slides.md -o presentation/slides.pdf
