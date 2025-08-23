.DEFAULT_GOAL := build

install-deps:
	@brew install pandoc basictex quarto mermaid-cli
	@quarto install --no-prompt extension pandoc-ext/diagram

build: slides.pdf conspect.pdf

slides.pdf: presentation/slides.md
	@echo "Building slides ..."
	@cd presentation && pandoc -t beamer -f markdown+implicit_figures slides.md -o slides.pdf

conspect.pdf: presentation/conspect.md
	@echo "Building conspect ..."
	@cd presentation && pandoc conspect.md -o conspect.pdf
