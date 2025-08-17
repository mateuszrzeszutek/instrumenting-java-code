.DEFAULT_GOAL := build

install-deps:
	@brew install pandoc basictex quarto mermaid-cli
	@quarto install --no-prompt extension pandoc-ext/diagram

build: slides.pdf conspect.pdf

slides.pdf: presentation/slides.md
	@echo "Building slides ..."
	@pandoc -t beamer -f markdown+implicit_figures presentation/slides.md -o presentation/slides.pdf

conspect.pdf: presentation/conspect.md
	@echo "Building conspect ..."
	@pandoc presentation/conspect.md -o presentation/conspect.pdf
